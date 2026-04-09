package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.ImportFoBulkRequest;
import com.mts.bulkhandling.mapper.Mapper;
import com.mts.bulkhandling.model.BsCfgReqType;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import com.mts.bulkhandling.model.WfWorkOrder;
import com.mts.bulkhandling.repository.BsCfgReqTypeRepository;
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
public class ImportFoService {

    @Autowired
    private WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;



    @Autowired
    private WfWorkOrderRepository wfWorkOrderRepository;

    @Autowired
    private BsCfgReqTypeRepository bsCfgReqTypeRepository;

    @Transactional
    public String execute(List<ImportFoBulkRequest> requests) {
        List<WfWoBulkQueue> records = new ArrayList<>();
        String sharedFileId = UUID.randomUUID().toString();

        for (ImportFoBulkRequest request : requests) {
            Optional<WfWorkOrder> workOrder = wfWorkOrderRepository.findById(request.getWorkOrderId());
            if (!workOrder.isPresent()) {
                throw new RuntimeException("Work order not found: " + request.getWorkOrderId());
            }
            BsCfgReqType reqType = bsCfgReqTypeRepository.findById(request.getRequestType()).orElse(null);
            request.setFileId(sharedFileId);
            WfWoBulkQueue wfWoBulkQueue = Mapper.FoBulkRequestToWfWoBulkQueue(request);
            wfWoBulkQueue.setWorkOrder(workOrder.get());
            if(reqType!=null)
                 wfWoBulkQueue.setBulkReqCategory(reqType.getBulkReqCategory());
            records.add(wfWoBulkQueue);

        }

        // Persist all records; saveAll returns the same list with IDs populated.
        List<WfWoBulkQueue> savedRecords = wfWoBulkCloseQueueRepository.saveAll(records);

        // Publish a Kafka event for each saved record so the consumer
        // can run FoValidation asynchronously.
        return sharedFileId;
    }
}

