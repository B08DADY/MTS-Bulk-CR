package com.mts.bulkhandling.mapper;

import com.mts.bulkhandling.dto.*;
import com.mts.bulkhandling.model.BsCfgReqType;
import com.mts.bulkhandling.model.LcPlaceDemographic;
import com.mts.bulkhandling.model.OrgRoleHierarchy;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import com.mts.bulkhandling.model.WfWorkOrder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class Mapper {

    // ── WfWoBulkQueue → HandledWoSearchResponse ───────────────────────────────

    public static HandledWoSearchResponse toHandledWoSearchResponse(WfWoBulkQueue entity) {
        HandledWoSearchResponse dto = new HandledWoSearchResponse();
        dto.setFileId(entity.getFileId());
        dto.setWorkOrderId(entity.getWorkOrderId());
        dto.setOrganizationUnit(entity.getOrganizationUnit());
        dto.setPlace(entity.getPlace());
        dto.setServiceId(entity.getServiceId());
        dto.setReferenceId(entity.getReferenceId());
        dto.setRequestType(entity.getRequestType());
        dto.setUserId(entity.getUserId());
        dto.setRecordStatus(entity.getRecordStatus());
        dto.setOrderStatus(entity.getOrderStatus());
        dto.setActionDate(entity.getActionDate());
        return dto;
    }

    // ── WfWorkOrder → OpenedWoSearchResponse ─────────────────────────────────

    public static OpenedWoSearchResponse toOpenedWoSearchResponse(WfWorkOrder wo) {
        List<WfWoBulkQueue> queues = wo.getBulkQueue();
        Optional<WfWoBulkQueue> firstQueue = (queues != null && !queues.isEmpty())
                ? Optional.of(queues.get(0))
                : Optional.empty();

        OpenedWoSearchResponse.OpenedWoSearchResponseBuilder builder =
                OpenedWoSearchResponse.builder()
                        .workOrderId(wo.getWorkOrderId())
                        .orgRoleName(wo.getOrgRoleName())
                        .placeId(wo.getPlaceId())
                        .serviceId(wo.getServiceId())
                        .referenceId(wo.getReferenceId())
                        .requestType(wo.getRequestType())
                        .requestTypeEn(wo.getRequestTypeEn())
                        .bulkStatus(wo.getBulkStatus());


        return builder.build();
    }

    // ── OrgRoleHierarchy → OrgRoleResponse ───────────────────────────────────

    public static OrgRoleResponse toOrgRoleResponse(OrgRoleHierarchy entity) {
        OrgRoleResponse dto = new OrgRoleResponse();
        dto.setOrgRoleName(entity.getOrgRoleName());
        return dto;
    }

    // ── LcPlaceDemographic → PlaceResponse ───────────────────────────────────

    public static PlaceResponse toPlaceResponse(LcPlaceDemographic entity) {
        PlaceResponse dto = new PlaceResponse();
        dto.setId(entity.getId());
        dto.setPlaceName(entity.getPlaceDesc());
        return dto;
    }


    public static ReqTypeResponse toReqTypeResponse(BsCfgReqType entity) {
        ReqTypeResponse dto = new ReqTypeResponse();
        dto.setRequestType(entity.getRequestType());
        return dto;
    }

    public static WfWoBulkQueue RetailSuccessBulkRequestToWfWoBulkQueue(ImportRetailSuccessBulkRequest dto) {

        WfWoBulkQueue entity = new WfWoBulkQueue();
        entity.setOrganizationUnit(dto.getOrganizationUnit());
        entity.setServiceId(dto.getServiceNumber());
        entity.setReferenceId(dto.getReferenceId());
        entity.setUserId(dto.getUserId());
        entity.setRequestType(dto.getRequestType());
        entity.setActionDate(LocalDateTime.now());
        entity.setWorkerId(dto.getWorkerId());
        entity.setCloseCode(dto.getCloseCode());
        entity.setRecordStatus("NEW");
        entity.setDeviceType(dto.getDeviceType());
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setMacAddress(dto.getMacAddress());
        entity.setFileId(dto.getFileId());

        entity.setValidationType("RETAIL_SUCCESS");


        return entity;
    }

    public static WfWoBulkQueue RetailFailBulkRequestToWfWoBulkQueue(ImportRetailFailBulkRequest dto,WfWorkOrder workOrder) {

        WfWoBulkQueue entity = new WfWoBulkQueue();
        entity.setOrganizationUnit(dto.getOrganizationUnit());
        entity.setServiceId(dto.getServiceNumber());
        entity.setReferenceId(dto.getReferenceId());
        entity.setUserId(dto.getUserId());
        entity.setRequestType(dto.getRequestType());
        entity.setActionDate(LocalDateTime.now());
        entity.setWorkerId(dto.getWorkerId());
        entity.setCloseCode(dto.getCloseCode());
        entity.setRecordStatus("NEW");
        entity.setFileId(dto.getFileId());
        entity.setValidationType("RETAIL_FAIL");
        entity.setPlace(workOrder.getPlaceName());
        entity.setWorkOrder(workOrder);


        return entity;
    }


    public static WfWoBulkQueue FoBulkRequestToWfWoBulkQueue(ImportFoBulkRequest dto,WfWorkOrder workOrder) {

        WfWoBulkQueue entity = new WfWoBulkQueue();
        entity.setOrganizationUnit(dto.getOrganizationUnit());
        entity.setServiceId(dto.getServiceNumber());
        entity.setReferenceId(dto.getReferenceId());
        entity.setUserId(dto.getUserId());
        entity.setRequestType(dto.getRequestType());

        entity.setActionDate(LocalDateTime.now());
        entity.setWorkerId(dto.getWorkerId());
        entity.setCloseCode(dto.getCloseCode());
        entity.setRecordStatus("NEW");
        entity.setBox(dto.getBox());
        entity.setCabinet(dto.getCabinet());
        entity.setFileId(dto.getFileId());
        entity.setValidationType("FO");

        entity.setPlace(workOrder.getPlaceName());
        entity.setWorkOrder(workOrder);



        return entity;
    }



}
