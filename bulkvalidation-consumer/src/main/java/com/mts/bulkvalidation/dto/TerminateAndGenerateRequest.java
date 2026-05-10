package com.mts.bulkvalidation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TerminateAndGenerateRequest {
    private String workId;
    private String notes;
    private String updateBy;
    private String categoryId;
    private String closeName;
    private String newStatus;
    private String newActivity;
    private String woId;
    private String instanceId;
    private String notGenerateProcess;
    private String sameOwnerFlag;
    private String requestType;
}
