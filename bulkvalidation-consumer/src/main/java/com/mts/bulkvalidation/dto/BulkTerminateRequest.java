package com.mts.bulkvalidation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BulkTerminateRequest {
    private String workOrderId;
    private String closeName;
    private String notes;
    private String reqType;
    private Long workId;
    private Long instanceId;
    private String userId;
    private String workerId;
}
