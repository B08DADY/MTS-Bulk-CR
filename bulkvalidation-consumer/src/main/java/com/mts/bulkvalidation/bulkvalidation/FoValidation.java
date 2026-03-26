package com.mts.bulkvalidation.bulkvalidation;

import com.mts.bulkvalidation.model.BsCfgReqType;
import com.mts.bulkvalidation.model.WfWoBulkQueue;
import com.mts.bulkvalidation.model.WfWorkOrder;
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

    @Autowired
    private BsCfgReqTypeRepository bsCfgReqTypeRepository;

    /**
     * Validates that box/cabinet are provided when the request category
     * is not "Fix Cross Connection" or "Resurvey".
     */
    public void validateFoAfterBulkQueue(WfWorkOrder workorder, WfWoBulkQueue queue) {

        BsCfgReqType reqType = bsCfgReqTypeRepository.findById(queue.getRequestType()).orElse(null);



        String category = reqType.getBulkReqCategory();
        boolean categoryExempt = "Fix Cross Connection".equals(category) || "Resurvey".equals(category);

        if (!categoryExempt && (queue.getBox() == null || queue.getCabinet() == null)) {
            log.warn("Box/Cabinet missing for FO queue id={}. Rejecting.", queue.getId());
            rejectWo(queue, workorder,"Box/Cabinet missing");
        }
    }
}
