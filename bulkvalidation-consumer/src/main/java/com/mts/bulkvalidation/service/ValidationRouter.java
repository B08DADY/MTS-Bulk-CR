package com.mts.bulkvalidation.service;

import com.mts.bulkvalidation.bulkvalidation.FoValidation;
import com.mts.bulkvalidation.bulkvalidation.RetailFailValidation;
import com.mts.bulkvalidation.bulkvalidation.RetailSuccessValidation;
import com.mts.bulkvalidation.bulkvalidation.Validation;
import com.mts.bulkvalidation.model.WfWoBulkQueue;
import com.mts.bulkvalidation.model.WfWorkOrder;
import com.mts.bulkvalidation.repository.WfWoBulkCloseQueueRepository;
import com.mts.bulkvalidation.repository.WfWorkOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Orchestrates the full validation flow for a single BulkQueueEvent:
 *   1. Loads entities from the DB
 *   2. Runs common validation (Validation.validateAfterBulkQueue)
 *   3. Routes to type-specific validation
 *   4. Marks the record "Validated" if all checks pass
 */
@Service
public class ValidationRouter {


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

    @EventListener(ApplicationReadyEvent.class)
    public void validate() {

        // ── 1. Load the bulk-queue record ────────────────────────────────────
        List<WfWoBulkQueue> queue = wfWoBulkCloseQueueRepository
                .findByRecordStatus("NEW");
        if (queue.isEmpty()) {
            return;
        }

        for(WfWoBulkQueue order:queue){
            String type=order.getValidationType();
            WfWorkOrder wo= wfWorkOrderRepository.findById(order.getWorkOrderId()).orElse(null);

            validation.validateAfterBulkQueue(wo, order);

            if ("Failed".equals(order.getRecordStatus())) {
                continue;
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
            if (!"Failed".equals(order.getRecordStatus())) {
                order.setRecordStatus("Pending Validation");
                wo.setBulkStatus("Pending Validation");
                wfWorkOrderRepository.save(wo);
                wfWoBulkCloseQueueRepository.save(order);


            }


        }




    }



}
