package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.ImportRetailSuccessBulkRequest;
import com.mts.bulkhandling.mapper.Mapper;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import com.mts.bulkhandling.model.WfWorkOrder;
import com.mts.bulkhandling.repository.WfWoBulkCloseQueueRepository;
import com.mts.bulkhandling.repository.WfWorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImportRetailSuccessService {

    @Autowired
    private WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;


    @Autowired
    private WfWorkOrderRepository wfWorkOrderRepository;

    @Transactional
    public String execute(List<ImportRetailSuccessBulkRequest> requests) {
        List<WfWoBulkQueue> records = new ArrayList<>();
        String sharedFileId = UUID.randomUUID().toString();

        for (ImportRetailSuccessBulkRequest request : requests) {

            Optional<WfWorkOrder> workOrder = wfWorkOrderRepository.findById(request.getWorkOrderId());
            if (!workOrder.isPresent()) {
                throw new RuntimeException("Work order not found: " + request.getWorkOrderId());
            }
            request.setFileId(sharedFileId);
            WfWoBulkQueue wfWoBulkQueue = Mapper.RetailSuccessBulkRequestToWfWoBulkQueue(request);
            wfWoBulkQueue.setWorkOrder(workOrder.get());
            records.add(wfWoBulkQueue);
        }

        // Persist all records; saveAll returns the same list with IDs populated.
        List<WfWoBulkQueue> savedRecords = wfWoBulkCloseQueueRepository.saveAll(records);

        // Publish a Kafka event for each saved record so the consumer
        // can run RetailSuccessValidation asynchronously.
        return sharedFileId;
    }
}

