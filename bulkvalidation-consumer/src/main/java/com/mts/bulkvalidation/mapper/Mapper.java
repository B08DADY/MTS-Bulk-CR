package com.mts.bulkvalidation.mapper;

import com.mts.bulkvalidation.dto.BulkTerminateRequest;
import com.mts.bulkvalidation.dto.TerminateAndGenerateRequest;
import com.mts.bulkvalidation.model.WfWoAdditionalAttribute;
import com.mts.bulkvalidation.model.WfWoBulkQueue;

import java.util.ArrayList;
import java.util.List;

public class Mapper {


    public static BulkTerminateRequest BulkQueueToBulkTerminateRequest(WfWoBulkQueue entity,Long instanceId) {
        BulkTerminateRequest request=new BulkTerminateRequest();
        request.setReqType(entity.getRequestType());
        request.setWorkOrderId(entity.getWorkOrderId());
        request.setNotes("Bulk order closed");
        request.setInstanceId(instanceId);
        request.setWorkId(entity.getWorkId());
        request.setCloseName(entity.getCloseName());
        request.setUserId(entity.getUserId());
        request.setWorkerId(entity.getWorkerId());
        request.setQueueId(entity.getId());


        return request;
    }
    public static TerminateAndGenerateRequest BulkQueueToTerminateRequest(WfWoBulkQueue entity,Long bulkWorkId,Long instanceId,Long itemSeq ){
        TerminateAndGenerateRequest request=new TerminateAndGenerateRequest();
        request.setNotes("Rejected");
        request.setUpdateBy("Bulk");
        request.setCategoryId("2");
        request.setCloseName("Rejected");
        request.setNewStatus("Completed ");

        if(itemSeq!=null)
            request.setNewActivity(itemSeq.toString());

        request.setWoId(entity.getWorkOrderId());
        request.setNotGenerateProcess("0");
        request.setSameOwnerFlag("0");

        if(bulkWorkId!=null)
            request.setWorkId(bulkWorkId.toString());

        if(instanceId!=null)
            request.setInstanceId(instanceId.toString());

        request.setRequestType(entity.getRequestType());





        return  request;
    }


    public static List<WfWoAdditionalAttribute> getAdditionalAttributes(WfWoBulkQueue order){

        WfWoAdditionalAttribute queueIdAtt= new WfWoAdditionalAttribute();
        queueIdAtt.setAttId(QueueIdMap.QUEUE_IDS.get(order.getRequestType())); // get by request type
        queueIdAtt.setWorkOrderId(order.getWorkOrderId());
        queueIdAtt.setAttValue(order.getId().toString());
        queueIdAtt.setSectionId(SectionIdMap.SECTION_IDS.get(order.getRequestType()));


        WfWoAdditionalAttribute serialNumberAtt= new WfWoAdditionalAttribute();
        serialNumberAtt.setAttId(SerialNumberIdMap.SERIAL_IDS.get(order.getRequestType())); // get by request type
        serialNumberAtt.setWorkOrderId(order.getWorkOrderId());
        serialNumberAtt.setAttValue(order.getSerialNumber());
        serialNumberAtt.setSectionId(SectionIdMap.SECTION_IDS.get(order.getRequestType()));

        WfWoAdditionalAttribute categoryAtt= new WfWoAdditionalAttribute();
        serialNumberAtt.setAttId(CategoryIdMap.CATEGORY_IDS.get(order.getRequestType())); // get by request type
        serialNumberAtt.setWorkOrderId(order.getWorkOrderId());
        serialNumberAtt.setAttValue(order.getBulkReqCategory());
        serialNumberAtt.setSectionId(SectionIdMap.SECTION_IDS.get(order.getRequestType()));

        List<WfWoAdditionalAttribute> attributes = new ArrayList<>();
        attributes.add(queueIdAtt);
        attributes.add(serialNumberAtt);

        return attributes;

    }
}
