package com.mts.bulkvalidation.service;

import com.mts.bulkvalidation.bulkvalidation.FoValidation;
import com.mts.bulkvalidation.bulkvalidation.RetailFailValidation;
import com.mts.bulkvalidation.bulkvalidation.RetailSuccessValidation;
import com.mts.bulkvalidation.bulkvalidation.Validation;
import com.mts.bulkvalidation.dto.BulkQueueEvent;
import com.mts.bulkvalidation.model.WfWoBulkQueue;
import com.mts.bulkvalidation.model.WfWorkOrder;
import com.mts.bulkvalidation.repository.WfWoBulkCloseQueueRepository;
import com.mts.bulkvalidation.repository.WfWorkOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Orchestrates the full validation flow for a single BulkQueueEvent:
 *   1. Loads entities from the DB
 *   2. Runs common validation (Validation.validateAfterBulkQueue)
 *   3. Routes to type-specific validation
 *   4. Marks the record "Validated" if all checks pass
 */
@Service
public class ValidationRouter {

    private static final Logger log = LoggerFactory.getLogger(ValidationRouter.class);

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

    /**
     * Entry point called by the Kafka listener for each incoming event.
     *
     * @param event the deserialized BulkQueueEvent published by the producer
     */
    public void route(BulkQueueEvent event) {

        // ── 1. Load the bulk-queue record ────────────────────────────────────
        WfWoBulkQueue queue = wfWoBulkCloseQueueRepository
                .findById(event.getQueueId())
                .orElse(null);

        if (queue == null) {
            log.error("BulkQueueEvent references unknown queue id={}. Skipping.", event.getQueueId());
            return;
        }

        // ── 2. Load the associated work order ───────────────────────────────
        WfWorkOrder workOrder = wfWorkOrderRepository
                .findById(queue.getWorkOrderId())
                .orElse(null);

        // ── 3. Common validation ────────────────────────────────────────────
        validation.validateAfterBulkQueue(workOrder, queue);

        // If already rejected, skip type-specific checks
        if ("Rejected".equals(queue.getRecordStatus())) {
            return;
        }

        // ── 4. Type-specific validation ─────────────────────────────────────
        String type = event.getValidationType();

        switch (type) {
            case "FO":
                foValidation.validateFoAfterBulkQueue(workOrder, queue);
                break;

            case "RETAIL_SUCCESS":
                // Build a lightweight DTO from the queue record so the validator
                // can access deviceType without a separate DB query.

                retailSuccessValidation.validateRetailSuccessAfterBulkQueue(workOrder, queue);
                break;

            case "RETAIL_FAIL":
                retailFailValidation.validateRetailFailAfterBulkQueue(workOrder, queue);
                break;

            default:
                log.warn("Unknown validationType '{}' for queue id={}. No type-specific validation run.", type, queue.getId());
                break;
        }

        // ── 5. Mark as Validated if still not Rejected ───────────────────────
        if (!"Rejected".equals(queue.getRecordStatus())) {
            queue.setRecordStatus("Accepted");
            wfWoBulkCloseQueueRepository.save(queue);
            log.info("Queue id={} validated successfully (type={}).", queue.getId(), type);
        }
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    /**
     * Maps the WfWoBulkQueue columns that carry retail-success fields into the
     * ImportRetailSuccessBulkRequest DTO expected by RetailSuccessValidation.
     */

}
