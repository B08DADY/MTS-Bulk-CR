package com.mts.bulkhandling.controller;

import com.mts.bulkhandling.dto.*;
import com.mts.bulkhandling.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller exposing the paginated dynamic work-order search endpoint.
 *
 * <p>POST /search
 * Accepts a JSON body of {@link WoSearchRequest} and returns a
 * paginated {@link Page} of {@link OpenedWoSearchResponse}.
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
public class SearchBulkController {

    @Autowired
    private  ReqTypeService reqTypeService;
    @Autowired
    private  OpenWorkOrderService openWorkOrderService;
    @Autowired
    private PlaceService placeService;

    @Autowired
    private OrgRoleService orgRoleService;

    @Autowired
    private HandlingBulkQueueService handlingBulkQueueService;

    @Autowired
    private BulkStatusService bulkStatusService;




    @PostMapping("/opend")
    public ResponseEntity<?> getOpenWork(
            @RequestBody WoSearchRequest request) {

        try {
            Page<OpenedWoSearchResponse> result = openWorkOrderService.search(request);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while searching: " + e.getMessage());
        }


    }

    @PostMapping("/handled")
    public ResponseEntity<?> search(
            @RequestBody WoSearchRequest request
    ) {
        try {
            Pageable pageable = PageRequest.of(request.getPage() ,request.getSize());
            Page<HandledWoSearchResponse> result = handlingBulkQueueService.search(request, pageable);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while searching: " + e.getMessage());
        }
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

    @GetMapping("/bulk-status")
    public ResponseEntity<List<String>> getBulkStatuses() {
        List<String> statuses = bulkStatusService.getAllStatuses();
        return ResponseEntity.ok(statuses);
    }

}
