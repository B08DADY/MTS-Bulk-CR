package com.mts.bulkhandling.bulkvalidation;

import com.mts.bulkhandling.dto.ImportBulkRequest;
import com.mts.bulkhandling.dto.ImportFoBulkRequest;
import com.mts.bulkhandling.model.BsCfgReqType;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import com.mts.bulkhandling.model.WfWorkOrder;
import com.mts.bulkhandling.repository.BsCfgReqTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FoValidation extends Validation {

    @Autowired
    private BsCfgReqTypeRepository bsCfgReqTypeRepository;

    public void ValidateFoAfterBulkQueue(WfWorkOrder workorder , WfWoBulkQueue queue){

        BsCfgReqType reqtype =  bsCfgReqTypeRepository.findById(queue.getRequestType()).orElse(null);

        if(! reqtype.getBulkReqCategory().equals("Fix Cross Connection")
                && !reqtype.getBulkReqCategory().equals("Resurvey")){
            if(queue.getBox()==null || queue.getCabinet()==null){
                RejectWo(queue, workorder);
            }
        }




    }
}
