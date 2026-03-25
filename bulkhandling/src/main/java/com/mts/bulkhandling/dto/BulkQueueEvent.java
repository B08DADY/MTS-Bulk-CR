package com.mts.bulkhandling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Kafka message payload published after each WfWoBulkQueue record is
 * persisted. The consumer uses the queueId to re-fetch the record from
 * the database and route it to the correct validator.
 *
 * Validation types:
 *   "FO"             → FoValidation
 *   "RETAIL_SUCCESS" → RetailSuccessValidation
 *   "RETAIL_FAIL"    → RetailFailValidation
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulkQueueEvent {

    /** Primary key of the persisted WfWoBulkQueue row. */
    private Long queueId;

    /** Discriminator that tells the consumer which validator to use. */
    private String validationType;

    /** Epoch-millisecond timestamp of the publish event (for traceability). */
    private Long timestamp;
}
