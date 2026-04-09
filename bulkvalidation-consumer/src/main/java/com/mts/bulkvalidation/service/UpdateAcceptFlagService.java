package com.mts.bulkvalidation.service;


import com.mts.bulkvalidation.bulkvalidation.Validation;
import com.mts.bulkvalidation.dto.BulkTerminateRequest;
import com.mts.bulkvalidation.model.WfWoBulkQueue;
import com.mts.bulkvalidation.model.WfWork;
import com.mts.bulkvalidation.model.WfWorkOrder;
import com.mts.bulkvalidation.repository.WfWorkOrderItemRepository;
import com.mts.bulkvalidation.repository.WfWorkRepository;
import com.mts.bulkvalidation.repository.projection.WorkInstanceProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateAcceptFlagService {

    @Autowired
    private WfWorkRepository wfWorkRepository;

    @Autowired
    private Validation validation;

    @Autowired
    private WfWorkOrderItemRepository workOrderItemRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void excute(WfWoBulkQueue order, WfWorkOrder wo) {
        Pageable pageable = PageRequest.of(0, 1);


        List<WorkInstanceProjection> results = workOrderItemRepository
                .findTopStartedWork(order.getWorkOrderId(), pageable);

        Long workId     = results.get(0).getWorkId();

        WfWork work = wfWorkRepository.findById(workId).orElse(null);
        if (work == null) {
            validation.rejectWo(order, wo, "Work ID does not exist");
        } else {
            work.setAcceptFlag(1L);
            wfWorkRepository.save(work);
        }
    }

}


