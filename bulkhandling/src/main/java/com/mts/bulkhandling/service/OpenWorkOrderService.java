package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.WorkOrderSearchRequest;
import com.mts.bulkhandling.dto.WorkOrderSearchResponse;
import com.mts.bulkhandling.model.WfWoBulkQueue;
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
public class OpenWorkOrderService {

    private final WfWorkOrderRepository workOrderRepository;

    public OpenWorkOrderService(WfWorkOrderRepository workOrderRepository) {
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
        return workOrderPage.map(WorkOrderSearchResponse::fromEntity);
    }


    private Sort buildSort(WorkOrderSearchRequest request) {
        Sort.Direction direction = "desc".equalsIgnoreCase(request.getSortDir())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return Sort.by(direction, request.getSortBy());
    }


}
