package com.mts.bulkvalidation.bulkvalidation;

import com.mts.bulkvalidation.model.BsCfgReqType;
import com.mts.bulkvalidation.model.WfWoBulkQueue;
import com.mts.bulkvalidation.model.WfWorkOrder;
import com.mts.bulkvalidation.repository.BsCfgReqTypeRepository;
import com.mts.bulkvalidation.repository.LkpDeviceTypesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Retail-Success-specific validation rules.
 * Validates that the device type is compatible with the request type's service type.
 */
@Component
public class RetailSuccessValidation extends Validation {

    private static final Logger log = LoggerFactory.getLogger(RetailSuccessValidation.class);

    @Autowired
    private BsCfgReqTypeRepository bsCfgReqTypeRepository;

    @Autowired
    private LkpDeviceTypesRepository lkpDeviceTypesRepository;

    /**
     * @param workorder the work-order entity
     * @param queue     the bulk-queue record being validated
     */
    public void validateRetailSuccessAfterBulkQueue(WfWorkOrder workorder,
                                                     WfWoBulkQueue queue) {

        BsCfgReqType reqType = bsCfgReqTypeRepository.findById(queue.getRequestType()).orElse(null);

        if (reqType == null) {
            log.warn("Request type '{}' not found for queue id={}. Rejecting.", queue.getRequestType(), queue.getId());
            rejectWo(queue, workorder);
            return;
        }

        boolean deviceValid = lkpDeviceTypesRepository
                .existsByDeviceTypeCodeAndServiceTypeCode(queue.getDeviceType(), reqType.getServiceType());

        if (!deviceValid) {
            log.warn("Device type '{}' incompatible with service type '{}' for queue id={}. Rejecting.",
                    queue.getDeviceType(), reqType.getServiceType(), queue.getId());
            rejectWo(queue, workorder);
        }
    }
}
