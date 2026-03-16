package com.mts.bulkhandling.dto;

import com.mts.bulkhandling.model.WfWoBulkQueue;
import com.mts.bulkhandling.model.WfWorkOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
//    private String woStage;
//    private String woStatus;
//    private String workOrderDesc;
//    private String customerId;
//    private String woPriority;
//    private Date   initiateDate;
//    private Date   closedDate;
//    private String requestTypeAr;
    private String requestTypeEn;
    private String bulkStatus;


    // ── From WF_WO_BULK_CLOSE_QUEUE ────────────────────────────────────────
//    private Long   queueId;
   // private Long   fileId;
   // private String recordStatus;
//    private String orderStatus;
//    private Date   actionDate;



    public static WorkOrderSearchResponse fromEntity(WfWorkOrder wo) {
        // Pick the first queue entry (the join already filters to valid ones)
        List<WfWoBulkQueue> queues = wo.getBulkQueue();
        Optional<WfWoBulkQueue> firstQueue = (queues != null && !queues.isEmpty())
                ? Optional.of(queues.get(0))
                : Optional.empty();

        WorkOrderSearchResponse.WorkOrderSearchResponseBuilder builder =
                WorkOrderSearchResponse.builder()
                        .workOrderId(wo.getWorkOrderId())
                        .orgRoleName(wo.getOrgRoleName())
                        .placeId(wo.getPlaceId())
                        .serviceId(wo.getServiceId())
                        .referenceId(wo.getReferenceId())
                        .requestType(wo.getRequestType())
//                        .woStage(wo.getWoStage())
//                        .woStatus(wo.getWoStatus())
//                        .workOrderDesc(wo.getWorkOrderDesc())
//                        .customerId(wo.getCustomerId())
//                        .woPriority(wo.getWoPriority())
//                        .initiateDate(wo.getInitiateDate())
//                        .closedDate(wo.getClosedDate())
//                        .requestTypeAr(wo.getRequestTypeAr())
                        .requestTypeEn(wo.getRequestTypeEn())
                        .bulkStatus(wo.getBulkStatus());

//        firstQueue.ifPresent(q -> builder
//                .queueId(q.getId())
//                .fileId(q.getFileId())
//                .recordStatus(q.getRecordStatus())
//                .orderStatus(q.getOrderStatus())
//                .actionDate(q.getActionDate()));

        return builder.build();
    }
}
