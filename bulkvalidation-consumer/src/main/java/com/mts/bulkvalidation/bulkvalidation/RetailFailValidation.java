package com.mts.bulkvalidation.bulkvalidation;

import com.mts.bulkvalidation.model.BsCfgReqClose;
import com.mts.bulkvalidation.model.BsCfgReqCloseId;
import com.mts.bulkvalidation.model.WfWoBulkQueue;
import com.mts.bulkvalidation.model.WfWorkOrder;
import org.springframework.stereotype.Component;

/**
 * Retail-Fail-specific validation rules.
 * Currently no additional rules beyond the common validation.
 * Framework is in place for future rules.
 */
@Component
public class RetailFailValidation extends Validation {

    /**
     * Runs Retail-Fail-specific post-queue validation.
     * Add rule implementations here when requirements are defined.
     */
    public void validateRetailFailAfterBulkQueue(WfWorkOrder workorder, WfWoBulkQueue queue) {
        // No additional rules currently — common validation in Validation.validateAfterBulkQueue() is sufficient.

        BsCfgReqCloseId id = new BsCfgReqCloseId(queue.getRequestType(), queue.getCloseCode());
        BsCfgReqClose reqClose = bsCfgReqCloseRepository.findById(id).orElse(null);


        if(reqClose.getCategory()!=2){
            rejectWo(queue, workorder,"This close code is not for retail fail");
        }

    }
}
