package com.mts.bulkvalidation.service;

import com.mts.bulkvalidation.bulkvalidation.FoValidation;
import com.mts.bulkvalidation.bulkvalidation.RetailFailValidation;
import com.mts.bulkvalidation.bulkvalidation.RetailSuccessValidation;
import com.mts.bulkvalidation.bulkvalidation.Validation;
import com.mts.bulkvalidation.dto.BulkTerminateRequest;
import com.mts.bulkvalidation.mapper.Mapper;
import com.mts.bulkvalidation.model.WfWoAdditionalAttribute;
import com.mts.bulkvalidation.model.WfWoBulkQueue;
import com.mts.bulkvalidation.model.WfWork;
import com.mts.bulkvalidation.model.WfWorkOrder;
import com.mts.bulkvalidation.repository.*;

import com.mts.bulkvalidation.repository.projection.WorkInstanceProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Orchestrates the full validation flow for a single BulkQueueEvent:
 *   1. Loads entities from the DB
 *   2. Runs common validation (Validation.validateAfterBulkQueue)
 *   3. Routes to type-specific validation
 *   4. Marks the record "Validated" if all checks pass
 */
@Service
public class ValidationRouterService {


    @Autowired
    private WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;

    @Autowired
    private WfWorkOrderRepository wfWorkOrderRepository;

    @Autowired
    private Validation validation;

    @Autowired
    private FoValidation foValidation;

    @Autowired
    private RetailSuccessValidation retailSuccessValidation;

    @Autowired
    private RetailFailValidation retailFailValidation;

    @Autowired
    private BulkTerminateAndGenerateService bulkTerminateAndGenerateService;


    @Autowired
    private  WfWorkOrderItemRepository workOrderItemRepository;

    @Autowired
    private WfWoAdditionalAttributeRepository wfWoAdditionalAttributeRepository;



    //@Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void validate() {

        // ── 1. Load the bulk-queue record ────────────────────────────────────
        List<WfWoBulkQueue> queue = wfWoBulkCloseQueueRepository
                .findByRecordStatus("NEW");
        if (queue.isEmpty()) {
            return;
        }

        for(WfWoBulkQueue order:queue){

            try {
                validateSingleOrder(order);
            }
            catch (Exception e){
              validation.rejectWoBulkQueue(order,e.getMessage());
            }

        }

    }


    //@Transactional
    public void validateSingleOrder(WfWoBulkQueue order){
        Pageable pageable = PageRequest.of(0, 1);
        String type=order.getValidationType();
        List<WorkInstanceProjection> results;

        WfWorkOrder wo= wfWorkOrderRepository.findById(order.getWorkOrderId()).orElse(null);
        if(order.getValidationType().equals("RETAIL_SUCCESS") || order.getValidationType().equals("RETAIL_FAIL")){
            results = workOrderItemRepository
                    .findTopRetailWork(order.getWorkOrderId(), pageable);
        }
        else{
            results = workOrderItemRepository
                    .findTopFoWork(order.getWorkOrderId(), pageable);
        }


        if (results.isEmpty()) {
            validation.rejectWo(order,wo,"Order Reached PONR");
        }

        Long workId     = results.get(0).getWorkId();
        Long instanceId = results.get(0).getInstanceId();


        order.setWorkId(workId);


        validation.validateAfterBulkQueue(wo, order);

        if ("Rejected".equals(order.getRecordStatus())) {
            return;
        }
        switch (type) {
            case "FO":
                foValidation.validateFoAfterBulkQueue(wo, order);
                break;

            case "RETAIL_SUCCESS":
                // Build a lightweight DTO from the queue record so the validator
                // can access deviceType without a separate DB query.

                retailSuccessValidation.validateRetailSuccessAfterBulkQueue(wo, order);
                break;

            case "RETAIL_FAIL":
                retailFailValidation.validateRetailFailAfterBulkQueue(wo, order);
                break;

            default:
                break;
        }
        if (!"Rejected".equals(order.getRecordStatus())) {
            order.setRecordStatus("Pending Validation");
            wo.setBulkStatus("Pending Validation");
            wfWorkOrderRepository.save(wo);
            wfWoBulkCloseQueueRepository.save(order);

            // call the terminate and the generate
            BulkTerminateRequest request= Mapper.BulkQueueToBulkTerminateRequest(order,instanceId);


                List<WfWoAdditionalAttribute> additionalAttributes=Mapper.getAdditionalAttributes(order);

                for(WfWoAdditionalAttribute attribute:additionalAttributes){
                    if(attribute.getAttId()!=null){
                        wfWoAdditionalAttributeRepository.save(attribute);
                    }
                }
            bulkTerminateAndGenerateService.execute(request);



            //activate


        }

    }



}
