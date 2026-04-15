package com.mts.bulkvalidation.bulkvalidation;

import com.mts.bulkvalidation.model.*;
import com.mts.bulkvalidation.repository.BsCfgReqCloseRepository;
import com.mts.bulkvalidation.repository.WfEmpRoleRepository;
import com.mts.bulkvalidation.repository.WfWoBulkCloseQueueRepository;
import com.mts.bulkvalidation.repository.WfWorkOrderRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Base validation logic executed after every bulk-queue record is saved.
 * Converted from a plain class to a Spring @Component so that @Autowired
 * repository injection works correctly.
 */
@Component
public class Validation {


    @Autowired
    protected WfWorkOrderRepository wfWorkOrderRepository;

    @Autowired
    protected WfWoBulkCloseQueueRepository wfWoBulkCloseQueueRepository;

    @Autowired
    protected BsCfgReqCloseRepository bsCfgReqCloseRepository;

    @Autowired
    protected WfEmpRoleRepository wfEmpRoleRepository;


    /**
     * Marks both the bulk-queue record and the work-order as "Rejected" and
     * persists both changes.
     */
    public void rejectWo(WfWoBulkQueue bulkOrder, WfWorkOrder wfWorkOrder,String reason ) {
        try {
            bulkOrder.setRecordStatus("Rejected");
            bulkOrder.setFailReason(reason);

            if (wfWorkOrder != null) {
                wfWorkOrder.setBulkStatus("Rejected");
                wfWorkOrderRepository.save(wfWorkOrder);
            }

            wfWoBulkCloseQueueRepository.save(bulkOrder);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void rejectWoBulkQueue(WfWoBulkQueue bulkOrder, String reason ) {
        try {
            bulkOrder.setRecordStatus("Rejected");
            bulkOrder.setFailReason(reason);

            wfWoBulkCloseQueueRepository.save(bulkOrder);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Runs the common cross-field validation rules that apply to every
     * import type (FO, Retail-Success, Retail-Fail).
     *
     * @param workorder the work-order fetched from the DB (may be null)
     * @param queue     the bulk-queue record being validated
     */
    public void validateAfterBulkQueue(WfWorkOrder workorder, WfWoBulkQueue queue) {

        boolean exists = wfWoBulkCloseQueueRepository
                .existsByWorkOrderIdAndRecordStatusIn(
                        queue.getWorkOrderId(),
                        Arrays.asList("Accepted", "Pending Validation")
                );

        if (exists) {
            rejectWoBulkQueue(queue,"Work order already success");
            return;
        }



        if(queue.getWorkOrderId()==null){
            rejectWo(queue, workorder,"Missing Mandatory Parameter");
            return;
        }
        if(queue.getWorkerId()==null){
            rejectWo(queue, workorder,"Missing Mandatory Parameter");
            return;
        }
        if(queue.getCloseCode()==null){
            rejectWo(queue, workorder,"Missing Mandatory Parameter");
            return;
        }

        if(queue.getOrganizationUnit()==null){
            rejectWo(queue, workorder,"Incomplete parameters");
            return;
        }

        if(queue.getReferenceId()==null){
            rejectWo(queue, workorder,"Incomplete parameters");
            return;
        }

        if(queue.getServiceId()==null){
            rejectWo(queue, workorder,"Incomplete parameters");
            return;
        }






        WfEmpRole empRole = wfEmpRoleRepository.findById(queue.getWorkerId()).orElse(null);
        BsCfgReqCloseId id = new BsCfgReqCloseId(queue.getRequestType(), queue.getCloseCode());
        BsCfgReqClose reqClose = bsCfgReqCloseRepository.findById(id).orElse(null);



        // WO must not already be closed
        if ("Close".equals(workorder.getWoStage())) {
            rejectWo(queue, workorder,"Invalid work order");
            return;
        }

        // Reference ID must match
//        if (!workorder.getReferenceId().equals(queue.getReferenceId())) {
//            rejectWo(queue, workorder);
//            return;
//        }

        // Service number must match
//        if (!workorder.getServiceId().equals(queue.getServiceId())) {
//            rejectWo(queue, workorder);
//            return;
//        }

        // Organisation unit must match
//        if (!workorder.getOrgRoleName().equals(queue.getOrganizationUnit())) {
//            rejectWo(queue, workorder);
//            return;
//        }

        // Close code (request_type + close_code combo) must exist

        if (reqClose == null) {
            rejectWo(queue, workorder,"Invalid Close Code");
            return;
        }

        // Worker ID must be a known employee role
        if (empRole == null) {
            rejectWo(queue, workorder,"Invalid worker id");
        }
    }
}
