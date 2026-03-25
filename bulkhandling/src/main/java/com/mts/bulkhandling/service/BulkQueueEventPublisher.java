package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.BulkQueueEvent;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

/**
 * Spring service responsible for publishing {@link BulkQueueEvent} messages
 * to the Kafka topic after a batch of WfWoBulkQueue records has been saved.
 *
 * <p>Publishing is <strong>fire-and-forget with callback</strong>: the HTTP
 * response is returned immediately after the DB insert; the Kafka send
 * completes asynchronously. If the broker is unavailable, the error is
 * logged (retries are configured via spring.kafka.producer.retries).
 */
@Service
public class BulkQueueEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(BulkQueueEventPublisher.class);

    private final KafkaTemplate<String, BulkQueueEvent> kafkaTemplate;

    @Value("${kafka.topic.bulk-queue-validation}")
    private String topic;

    public BulkQueueEventPublisher(KafkaTemplate<String, BulkQueueEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes one Kafka event per saved {@link WfWoBulkQueue} record.
     *
     * @param savedRecords   the persisted records (must already have a non-null ID)
     * @param validationType one of "FO", "RETAIL_SUCCESS", "RETAIL_FAIL"
     */
    public void publish(List<WfWoBulkQueue> savedRecords, String validationType) {
        if (savedRecords == null || savedRecords.isEmpty()) {
            return;
        }

        for (WfWoBulkQueue record : savedRecords) {
            if (record.getId() == null) {
                log.warn("Skipping Kafka publish: WfWoBulkQueue record has null ID (may not be persisted yet).");
                continue;
            }

            BulkQueueEvent event = BulkQueueEvent.builder()
                    .queueId(record.getId())
                    .validationType(validationType)
                    .timestamp(System.currentTimeMillis())
                    .build();

            // Use queueId as the Kafka message key so events for the same
            // record always go to the same partition (ordering guarantee).
            String messageKey = String.valueOf(record.getId());

            ListenableFuture<SendResult<String, BulkQueueEvent>> future =
                    kafkaTemplate.send(topic, messageKey, event);

            future.addCallback(new ListenableFutureCallback<SendResult<String, BulkQueueEvent>>() {
                @Override
                public void onSuccess(SendResult<String, BulkQueueEvent> result) {
                    log.info("Published BulkQueueEvent: queueId={}, type={}, offset={}",
                            event.getQueueId(),
                            event.getValidationType(),
                            result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.error("Failed to publish BulkQueueEvent: queueId={}, type={}, error={}",
                            event.getQueueId(),
                            event.getValidationType(),
                            ex.getMessage(), ex);
                }
            });
        }
    }
}
