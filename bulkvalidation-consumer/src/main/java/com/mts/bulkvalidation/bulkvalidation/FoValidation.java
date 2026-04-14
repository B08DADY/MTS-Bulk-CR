package com.mts.bulkvalidation.bulkvalidation;

import com.mts.bulkvalidation.model.*;
import com.mts.bulkvalidation.repository.BsCfgReqTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FO-specific validation rules, executed after the common Validation checks pass.
 * Inherits rejectWo() and repository fields from Validation.
 */
@Component
public class FoValidation extends Validation {

    private static final Logger log = LoggerFactory.getLogger(FoValidation.class);


    /**
     * Validates that box/cabinet are provided when the request category
     * is not "Fix Cross Connection" or "Resurvey".
     */
    public void validateFoAfterBulkQueue(WfWorkOrder workorder, WfWoBulkQueue queue) {


        BsCfgReqCloseId id = new BsCfgReqCloseId(queue.getRequestType(), queue.getCloseCode());
        BsCfgReqClose reqClose = bsCfgReqCloseRepository.findById(id).orElse(null);


        if(reqClose.getCategory()!=1){
            rejectWo(queue, workorder,"This close code is not for FO");
        }

        String category = queue.getBulkReqCategory();
        boolean categoryExempt = "Fix Cross Connection".equals(category) || "Resurvey".equals(category);

        if (!categoryExempt && (queue.getBox() == null || queue.getCabinet() == null)) {
            log.warn("Box/Cabinet missing for FO queue id={}. Rejecting.", queue.getId());
            rejectWo(queue, workorder,"Box/Cabinet missing");
        }
    }
}
