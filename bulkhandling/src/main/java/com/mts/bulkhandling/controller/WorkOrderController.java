package com.mts.bulkhandling.controller;

import com.mts.bulkhandling.dto.WorkOrderSearchRequest;
import com.mts.bulkhandling.dto.WorkOrderSearchResponse;
import com.mts.bulkhandling.service.WorkOrderService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/search")
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    /**
     * Search work orders dynamically with pagination.
     *
     * @param request filter + pagination parameters (all optional except pagination defaults)
     * @return paginated list of matching work orders
     */
    @PostMapping
    public ResponseEntity<Page<WorkOrderSearchResponse>> search(
            @RequestBody WorkOrderSearchRequest request) {

        Page<WorkOrderSearchResponse> result = workOrderService.search(request);
        return ResponseEntity.ok(result);
    }
}
