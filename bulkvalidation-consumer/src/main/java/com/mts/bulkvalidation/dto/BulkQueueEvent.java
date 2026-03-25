package com.mts.bulkvalidation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Consumer-side mirror of the producer's BulkQueueEvent.
 * Must match the producer's field structure exactly for JSON deserialization.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulkQueueEvent {

    /** Primary key of the persisted WfWoBulkQueue row. */
    private Long queueId;

    /** One of: "FO", "RETAIL_SUCCESS", "RETAIL_FAIL" */
    private String validationType;

    /** Epoch-millisecond publish timestamp (for traceability). */
    private Long timestamp;
}
