package com.mts.bulkhandling.bulkvalidation;

import com.mts.bulkhandling.dto.ImportBulkRequest;
import com.mts.bulkhandling.dto.ImportFoBulkRequest;
import com.mts.bulkhandling.mapper.Mapper;
import com.mts.bulkhandling.model.*;
import com.mts.bulkhandling.repository.BsCfgReqCloseRepository;
import com.mts.bulkhandling.repository.WfEmpRoleRepository;
import com.mts.bulkhandling.repository.WfWoBulkCloseQueueRepository;
import com.mts.bulkhandling.repository.WfWorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Validation {

    static boolean reject=true;

    WfWorkOrder workorder;
    // Before insert in the queue
    @Autowired
    private  WfWorkOrderRepository wfWorkOrderRepository;

    @Autowired
    private WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;

    @Autowired
    private BsCfgReqCloseRepository bsCfgReqCloseRepository;
    @Autowired
    private WfEmpRoleRepository wfEmpRoeRepository;


    public void RejectWo(WfWoBulkQueue bulkorder,WfWorkOrder wfworkorder){
        bulkorder.setRecordStatus("Rejected");
        wfWoBulkCloseQueueRepository.save(bulkorder);

        wfworkorder.setBulkStatus("Rejected");
        wfWorkOrderRepository.save(wfworkorder);
    }


    public void ValidateAfterBulkQueue(WfWorkOrder workorder , WfWoBulkQueue queue){

          //  WfWorkOrder workorder = wfWorkOrderRepository.findById(order.getWorkOrderId()).orElse(null);
            WfEmpRole emprole= wfEmpRoeRepository.findById(queue.getWorkerId()).orElse(null);
           BsCfgReqCloseId id = new BsCfgReqCloseId(queue.getRequestType(), queue.getCloseCode());
           BsCfgReqClose reqclose= bsCfgReqCloseRepository.findById(id).orElse(null);
            // check the wok_order_id
            if(workorder==null){
                queue.setRecordStatus("Rejected");
                wfWoBulkCloseQueueRepository.save(queue);
            }
            else {
                // check the stage
                if (workorder.getWoStage().equals("Close")) {
                    RejectWo(queue,workorder);
                }
                // check the reference id

                if (!workorder.getReferenceId().equals(queue.getReferenceId())) {
                    RejectWo(queue,workorder);
                }
                // check the service number
                if (!workorder.getServiceId().equals(queue.getServiceId())) {
                    RejectWo(queue,workorder);
                }
                // check the organization
                if (!workorder.getOrgRoleName().equals(queue.getOrganizationUnit())) {
                    RejectWo(queue,workorder);
                }

                // close code (request_type_close)
                if(reqclose ==null){
                    RejectWo(queue,workorder);
                }

                // worker id
                if(emprole ==null){
                    RejectWo(queue,workorder);
                }


            }

    }


    public void validateBeforeBulkQueue(List<ImportBulkRequest> orders){

        for(ImportBulkRequest order : orders){
                reject = true;

            if(order.getWorkOrderId() == null)
                reject=false;

            if(order.getReferenceId() == null)
                reject=false;

            if(order.getServiceNumber() == null)
                reject=false;

            if(order.getOrganizationUnit() == null)
                reject=false;

            if(order.getWorkerId() == null)
                reject=false;

            if(order.getCloseCode() == null)
                reject=false;

//            if(order.getSerialNumber() == null)
//                throw new RuntimeException("Serial Number is required");
//
//            if(order.getMacAddress() == null)
//                throw new RuntimeException("MAC Address is required");
//
//            if(order.getDeviceType() == null)
//                throw new RuntimeException("Device Type is required");
        }
    }

}
