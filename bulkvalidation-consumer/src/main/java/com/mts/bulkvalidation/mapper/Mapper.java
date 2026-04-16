package com.mts.bulkvalidation.mapper;

import com.mts.bulkvalidation.dto.BulkTerminateRequest;
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


        return request;
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
