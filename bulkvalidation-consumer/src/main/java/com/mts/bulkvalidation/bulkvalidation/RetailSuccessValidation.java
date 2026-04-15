package com.mts.bulkvalidation.bulkvalidation;

import com.mts.bulkvalidation.model.*;
import com.mts.bulkvalidation.repository.BsCfgReqTypeRepository;
import com.mts.bulkvalidation.repository.LkpDeviceTypesRepository;
import com.mts.bulkvalidation.repository.WfWoBulkCloseQueueRepository;
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

    @Autowired
    protected WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;


    /**
     * @param workorder the work-order entity
     * @param queue     the bulk-queue record being validated
     */
    public void validateRetailSuccessAfterBulkQueue(WfWorkOrder workorder,
                                                     WfWoBulkQueue queue) {

        if (queue.getDeviceType()==null) {
            rejectWo(queue, workorder,"Incomplete parameters");
            return;
        }
        if (queue.getSerialNumber()==null) {
            rejectWo(queue, workorder,"Incomplete parameters");
            return;
        }

        BsCfgReqCloseId id = new BsCfgReqCloseId(queue.getRequestType(), queue.getCloseCode());
        BsCfgReqClose reqClose = bsCfgReqCloseRepository.findById(id).orElse(null);


        if(reqClose.getCategory()!=1){
            rejectWo(queue, workorder,"Invalid Close Code");
        }



        BsCfgReqType reqType = bsCfgReqTypeRepository.findById(queue.getRequestType()).orElse(null);

        if (reqType == null) {
            log.warn("Request type '{}' not found for queue id={}. Rejecting.", queue.getRequestType(), queue.getId());
            rejectWo(queue, workorder,"Invalid Request Type");
            return;
        }

        boolean deviceValid = lkpDeviceTypesRepository
                .existsByDeviceTypeCodeAndServiceTypeCode(queue.getDeviceType(), reqType.getServiceType());

        if (!deviceValid) {
            log.warn("Device type '{}' incompatible with service type '{}' for queue id={}. Rejecting.",
                    queue.getDeviceType(), reqType.getServiceType(), queue.getId());
            rejectWo(queue, workorder,"Invalid technical data");
        }
    }
}
