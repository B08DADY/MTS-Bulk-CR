package com.mts.bulkhandling.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkOrderSearchResponse {

    // ── From WF_WORK_ORDER ─────────────────────────────────────────────────
    private String workOrderId;
    private String orgRoleName;
    private String placeId;
    private String serviceId;
    private String referenceId;
    private String requestType;
    private String woStage;
    private String woStatus;
    private String workOrderDesc;
    private String customerId;
    private String woPriority;
    private Date   initiateDate;
    private Date   closedDate;
    private String requestTypeAr;
    private String requestTypeEn;

    // ── From WF_WO_BULK_CLOSE_QUEUE ────────────────────────────────────────
    private Long   queueId;
    private Long   fileId;
    private String recordStatus;
    private String orderStatus;
    private Date   actionDate;
}
