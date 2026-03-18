package com.mts.bulkhandling.bulkvalidation;

import com.mts.bulkhandling.dto.ImportBulkRequest;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import com.mts.bulkhandling.model.WfWorkOrder;
import com.mts.bulkhandling.repository.WfWoBulkCloseQueueRepository;
import com.mts.bulkhandling.repository.WfWorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Validation {
    // Before insert in the queue
    @Autowired
    private  WfWorkOrderRepository wfWorkOrderRepository;

    @Autowired
    private WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;


    public void RejectWo(WfWoBulkQueue bulkorder,WfWorkOrder wfworkorder){
        bulkorder.setRecordStatus("Rejected");
        wfWoBulkCloseQueueRepository.save(bulkorder);

        wfworkorder.setBulkStatus("Rejected");
        wfWorkOrderRepository.save(wfworkorder);
    }


    public void ValidateAfterBulkQueue(WfWoBulkQueue order){

            WfWorkOrder wo = wfWorkOrderRepository.findById(order.getWorkOrderId()).orElse(null);
            // check the wok_order_id
            if(wo==null){
                order.setRecordStatus("Rejected");
                wfWoBulkCloseQueueRepository.save(order);
            }
            else {
                // check the stage
                if (wo.getWoStage().equals("Close")) {
                    RejectWo(order,wo);
                }
                // check the reference id

                if (!wo.getReferenceId().equals(order.getReferenceId())) {
                    RejectWo(order,wo);
                }
                // check the service number
                if (!wo.getServiceId().equals(order.getServiceId())) {
                    RejectWo(order,wo);
                }
                // check the organization
                if (!wo.getOrgRoleName().equals(order.getOrganizationUnit())) {
                    RejectWo(order,wo);
                }

                // close code (request_type_close)



                // worker id








            }





    }


}
