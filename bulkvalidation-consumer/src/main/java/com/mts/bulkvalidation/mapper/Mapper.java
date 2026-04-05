package com.mts.bulkvalidation.mapper;

import com.mts.bulkvalidation.dto.BulkTerminateRequest;
import com.mts.bulkvalidation.model.WfWoBulkQueue;

import java.time.LocalDateTime;

public class Mapper {


    public static BulkTerminateRequest BulkQueueToBulkTerminateRequest(WfWoBulkQueue entity,Long instanceId) {
        BulkTerminateRequest request=new BulkTerminateRequest();
        request.setCloseName("Bulk Close");
        request.setReqType(entity.getRequestType());
        request.setWorkOrderId(entity.getWorkOrderId());
        request.setNotes("Close this Bulk order");
        request.setInstanceId(instanceId);
        request.setWorkId(entity.getWorkId());


        return request;
    }
}
