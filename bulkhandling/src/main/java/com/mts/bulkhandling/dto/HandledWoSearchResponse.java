package com.mts.bulkhandling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HandledWoSearchResponse {
    private String fileId;
    private String workOrderId;
    private String organizationUnit;
    private String place;
    private String serviceId;
    private String referenceId;
    private String requestType;
    private String userId;
    private String recordStatus;
    private String orderStatus;
    private LocalDateTime actionDate;
}
