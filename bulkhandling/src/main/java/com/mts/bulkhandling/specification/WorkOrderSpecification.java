package com.mts.bulkhandling.specification;

import com.mts.bulkhandling.dto.WorkOrderSearchRequest;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import com.mts.bulkhandling.model.WfWorkOrder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Builds a JPA {@link Specification} for searching {@link WfWorkOrder} records
 * with a mandatory INNER JOIN to {@link WfWoBulkQueue}.
 *
 * <p>Hardcoded constraints (always applied):
 * <ol>
 *   <li>wf_work_order.WO_STAGE IN ('Assign')</li>
 *   <li>wf_wo_bulk_close_queue.RECORD_STATUS &lt;&gt; 'pending validation'</li>
 * </ol>
 */
public class WorkOrderSpecification {

    private static final String PENDING_VALIDATION = "pending validation";
    private static final List<String> STAGE_ASSIGN = Arrays.asList("Schedule", "Assign");

    private WorkOrderSpecification() {
        // utility class – no instantiation
    }

    /**
     * Builds a {@link Specification} from the given search request.
     *
     * @param request the search request (fields may be null/empty)
     * @return a Specification combining hardcoded and dynamic predicates
     */
    public static Specification<WfWorkOrder> buildSearchSpec(WorkOrderSearchRequest request) {
        return (Root<WfWorkOrder> wfWorkOrderRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            // JOIN wf_work_order → wf_wo_bulk_close_queue (INNER JOIN)
//            Join<WfWorkOrder, WfWoBulkQueue> queueJoin =
//                    wfWorkOrderRoot.join("bulkQueue", JoinType.INNER);

            // Deduplicate root when fetching (avoids N+1 duplicates with pagination)
            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            // ── HARDCODED CONSTRAINTS ─────────────────────────────────────────
            // 1. WO_STAGE must be IN ('Assign')
            predicates.add(wfWorkOrderRoot.get("woStage").in(STAGE_ASSIGN));

            // 2. RECORD_STATUS must NOT equal 'pending validation'
//            predicates.add(cb.notEqual(
//                    cb.lower(wfWorkOrderRoot.get("bulkStatus")),
//                    PENDING_VALIDATION.toLowerCase()));

            // ── DYNAMIC FILTERS (null-safe) ───────────────────────────────────

            // workOrderId
            if (hasText(request.getWorkOrderId())) {
                predicates.add(cb.equal(wfWorkOrderRoot.get("workOrderId"), request.getWorkOrderId().trim()));
            }

            // organization → ORG_ROLE_NAME
            if (hasText(request.getOrganization())) {
                predicates.add(cb.equal(wfWorkOrderRoot.get("orgRoleName"), request.getOrganization().trim()));
            }

            // place → PLACE_ID
            if (hasText(request.getPlace())) {
                predicates.add(cb.equal(wfWorkOrderRoot.get("placeId"), request.getPlace().trim()));
            }

            // serviceId → SERVICE_ID
            if (hasText(request.getServiceId())) {
                predicates.add(cb.equal(wfWorkOrderRoot.get("serviceId"), request.getServiceId().trim()));
            }

            // referenceId → REFERENCE_ID
            if (hasText(request.getReferenceId())) {
                predicates.add(cb.equal(wfWorkOrderRoot.get("referenceId"), request.getReferenceId().trim()));
            }

            // requestType → REQUEST_TYPE
            if (hasText(request.getRequestType())) {
                predicates.add(cb.equal(wfWorkOrderRoot.get("requestType"), request.getRequestType().trim()));
            }

            // fileId → WF_WO_BULK_CLOSE_QUEUE.FILE_ID
//            if (request.getFileId() != null) {
//                predicates.add(cb.equal(queueJoin.get("fileId"), request.getFileId()));
//            }

            // recordStatus → WF_WO_BULK_CLOSE_QUEUE.RECORD_STATUS
            // User-supplied value is applied ONLY when it does not equal 'pending validation';
            // the hardcoded exclusion above already blocks that value.


            if (hasText(request.getBulkStatus())
                    && !PENDING_VALIDATION.equalsIgnoreCase(request.getBulkStatus().trim())) {

                if ("NEW".equalsIgnoreCase(request.getBulkStatus().trim())) {
                    // Match rows where bulkStatus = 'NEW' OR bulkStatus IS NULL
                    predicates.add(cb.or(
                            cb.equal(wfWorkOrderRoot.get("bulkStatus"), request.getBulkStatus().trim()),
                            cb.isNull(wfWorkOrderRoot.get("bulkStatus"))
                    ));
                } else {
                    predicates.add(cb.equal(wfWorkOrderRoot.get("bulkStatus"), request.getBulkStatus().trim()));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    /** Returns true if the string is non-null and non-blank. */
    private static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
