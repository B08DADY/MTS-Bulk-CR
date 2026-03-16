package com.mts.bulkhandling.controller;

import com.mts.bulkhandling.dto.*;
import com.mts.bulkhandling.service.OpenWorkOrderService;
import com.mts.bulkhandling.service.OrgRoleService;
import com.mts.bulkhandling.service.PlaceService;
import com.mts.bulkhandling.service.ReqTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller exposing the paginated dynamic work-order search endpoint.
 *
 * <p>POST /search
 * Accepts a JSON body of {@link WorkOrderSearchRequest} and returns a
 * paginated {@link Page} of {@link WorkOrderSearchResponse}.
 *
 * <p>Example request body:
 * <pre>
 * {
 *   "organization": "SomeOrg",
 *   "requestType": "INSTALLATION",
 *   "fileId": 123,
 *   "recordStatus": "processed",
 *   "page": 0,
 *   "size": 10,
 *   "sortBy": "workOrderId",
 *   "sortDir": "asc"
 * }
 * </pre>
 */
@RestController
@RequestMapping("/api/search")
public class WorkOrderController {

    @Autowired
    private  ReqTypeService reqTypeService;
    @Autowired
    private  OpenWorkOrderService openWorkOrderService;
    @Autowired
    private PlaceService placeService;

    @Autowired
    private OrgRoleService orgRoleService;




    @PostMapping("/opend")
    public ResponseEntity<Page<WorkOrderSearchResponse>> getOpenWork(
            @RequestBody WorkOrderSearchRequest request) {

        Page<WorkOrderSearchResponse> result = openWorkOrderService.search(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/request-types/fo-ftth")
    public ResponseEntity<List<ReqTypeResponse>> getFoFtthRequestTypes(){
        return ResponseEntity.ok(reqTypeService.getFoFtthRequestTypes());

    }

    @GetMapping("/places")
    public ResponseEntity<List<PlaceResponse>> getPlacesByOrganization(
            @RequestParam("organization") String organization) {
        return ResponseEntity.ok(placeService.getPlacesByOrganization(organization));
    }

    @GetMapping("/organizations/hierarchy")
    public ResponseEntity<List<OrgRoleResponse>> getOrgHierarchy(
            @RequestParam("orgRoleName") String orgRoleName) {
        return ResponseEntity.ok(orgRoleService.getOrgHierarchy(orgRoleName));
    }



}
