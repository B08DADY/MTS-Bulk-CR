package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.WorkOrderSearchRequest;
import com.mts.bulkhandling.dto.WorkOrderSearchResponse;
import com.mts.bulkhandling.model.WfWoBulkCloseQueue;
import com.mts.bulkhandling.model.WfWorkOrder;
import com.mts.bulkhandling.repository.WfWorkOrderRepository;
import com.mts.bulkhandling.specification.WorkOrderSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WorkOrderService {

    private final WfWorkOrderRepository workOrderRepository;

    public WorkOrderService(WfWorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
    }

    /**
     * Executes a paginated, dynamic search over WF_WORK_ORDER joined to
     * WF_WO_BULK_CLOSE_QUEUE using the given request criteria.
     *
     * @param request search filters + pagination parameters
     * @return a {@link Page} of {@link WorkOrderSearchResponse} DTOs
     */
    public Page<WorkOrderSearchResponse> search(WorkOrderSearchRequest request) {
        Sort sort = buildSort(request);
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Specification<WfWorkOrder> spec = WorkOrderSpecification.buildSearchSpec(request);

        Page<WfWorkOrder> workOrderPage = workOrderRepository.findAll(spec, pageable);
        return workOrderPage.map(this::toResponse);
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private Sort buildSort(WorkOrderSearchRequest request) {
        Sort.Direction direction = "desc".equalsIgnoreCase(request.getSortDir())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return Sort.by(direction, request.getSortBy());
    }

    private WorkOrderSearchResponse toResponse(WfWorkOrder wo) {
        // Pick the first queue entry (the join already filters to valid ones)
        List<WfWoBulkCloseQueue> queues = wo.getBulkCloseQueues();
        Optional<WfWoBulkCloseQueue> firstQueue = (queues != null && !queues.isEmpty())
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
                        .woStage(wo.getWoStage())
                        .woStatus(wo.getWoStatus())
                        .workOrderDesc(wo.getWorkOrderDesc())
                        .customerId(wo.getCustomerId())
                        .woPriority(wo.getWoPriority())
                        .initiateDate(wo.getInitiateDate())
                        .closedDate(wo.getClosedDate())
                        .requestTypeAr(wo.getRequestTypeAr())
                        .requestTypeEn(wo.getRequestTypeEn());

        firstQueue.ifPresent(q -> builder
                .queueId(q.getId())
                .fileId(q.getFileId())
                .recordStatus(q.getRecordStatus())
                .orderStatus(q.getOrderStatus())
                .actionDate(q.getActionDate()));

        return builder.build();
    }
}
