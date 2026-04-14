package com.mts.bulkhandling.specification;

import com.mts.bulkhandling.dto.WoSearchRequest;
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
    // not analyze ready
    private WorkOrderSpecification() {
        // utility class – no instantiation
    }

    /**
     * Builds a {@link Specification} from the given search request.
     *
     * @param request the search request (fields may be null/empty)
     * @return a Specification combining hardcoded and dynamic predicates
     */
    public static Specification<WfWorkOrder> buildSearchSpec(WoSearchRequest request) {
        return (Root<WfWorkOrder> wfWorkOrderRoot, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            // ── HARDCODED CONSTRAINTS ─────────────────────────────────────────
            predicates.add(wfWorkOrderRoot.get("woStage").in(STAGE_ASSIGN));

            // ── DYNAMIC FILTERS (null-safe) ───────────────────────────────────

            // fileId → join WfWoBulkQueue and filter by FILE_ID (only when provided)
            if (hasText(request.getFileId())) {
                Join<WfWorkOrder, WfWoBulkQueue> queueJoin =
                        wfWorkOrderRoot.join("bulkQueue", JoinType.INNER);
                predicates.add(cb.equal(queueJoin.get("fileId"), request.getFileId().trim()));
            }

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

            // bulkStatus
            if (hasText(request.getBulkStatus())
                    && !PENDING_VALIDATION.equalsIgnoreCase(request.getBulkStatus().trim())) {

                if ("NEW".equalsIgnoreCase(request.getBulkStatus().trim())) {
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
