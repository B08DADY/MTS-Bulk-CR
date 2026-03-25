package com.mts.bulkhandling.bulkvalidation;

import com.mts.bulkhandling.dto.ImportBulkRequest;
import com.mts.bulkhandling.dto.ImportRetailSuccessBulkRequest;
import com.mts.bulkhandling.model.BsCfgReqType;
import com.mts.bulkhandling.model.LkpDeviceTypes;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import com.mts.bulkhandling.model.WfWorkOrder;
import com.mts.bulkhandling.repository.BsCfgReqTypeRepository;
import com.mts.bulkhandling.repository.LkpDeviceTypesRepository;
import com.mts.bulkhandling.repository.WfWoBulkCloseQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RetailSuccessValidation extends Validation {

    @Autowired
    private BsCfgReqTypeRepository bsCfgReqTypeRepository;

    @Autowired
    private WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;

    @Autowired
    private LkpDeviceTypesRepository  lkpDeviceTypesRepository;
    public void ValidateRetailSuccessAfterBulkQueue(WfWorkOrder workorder , WfWoBulkQueue queue){

        //Chek Device Type
       BsCfgReqType reqtype= bsCfgReqTypeRepository.findById(queue.getRequestType()).orElse(null);
       if(!lkpDeviceTypesRepository.existsByDeviceTypeCodeAndServiceTypeCode(queue.getDeviceType(),reqtype.getServiceType())){
           RejectWo(queue,workorder);
        }




    }

//    public void ValidateRetailSuccessBeforeBulkQueue (List<ImportRetailSuccessBulkRequest> orders){
//
//        for(ImportRetailSuccessBulkRequest order : orders) {
//            if (order.getDeviceType() == null){
//
//            }
//
//        }
//    }

}
