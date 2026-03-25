package com.mts.bulkvalidation.kafka;

import com.mts.bulkvalidation.dto.BulkQueueEvent;
import com.mts.bulkvalidation.service.ValidationRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Kafka listener that consumes events from the bulk-queue-validation topic
 * and delegates the full validation flow to ValidationRouter.
 *
 * Keeping this class thin ensures the listener is easy to test and reason about:
 * all business logic lives in ValidationRouter and the validation components.
 */
@Component
public class BulkQueueEventListener {

    private static final Logger log = LoggerFactory.getLogger(BulkQueueEventListener.class);

    @Autowired
    private ValidationRouter validationRouter;

    /**
     * Invoked once per message received from the topic.
     *
     * @param event     the deserialized BulkQueueEvent payload
     * @param partition the Kafka partition (logged for traceability)
     * @param offset    the message offset  (logged for traceability)
     */
    @KafkaListener(
            topics = "${kafka.topic.bulk-queue-validation}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void onBulkQueueEvent(
            @Payload BulkQueueEvent event,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {

        log.info("Received BulkQueueEvent: queueId={}, type={}, partition={}, offset={}",
                event.getQueueId(), event.getValidationType(), partition, offset);

        try {
            validationRouter.route(event);
        } catch (Exception ex) {
            // Log the failure but do NOT re-throw: rethrowing would cause Kafka to
            // retry indefinitely. In production, configure a DeadLetterPublishingRecoverer.
            log.error("Unhandled error processing queueId={}: {}", event.getQueueId(), ex.getMessage(), ex);
        }
    }
}
