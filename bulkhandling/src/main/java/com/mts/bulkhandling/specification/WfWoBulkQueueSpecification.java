package com.mts.bulkhandling.specification;

import com.mts.bulkhandling.dto.HandledWoSearchResponse;
import com.mts.bulkhandling.dto.WoSearchRequest;
import com.mts.bulkhandling.model.WfWoBulkQueue;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class WfWoBulkQueueSpecification {
    public static Specification<WfWoBulkQueue> filter(
            WoSearchRequest request
    ) {
        return (root, query, criteriaBuilder) -> {
            Predicate p = criteriaBuilder.conjunction();

            if(request.getFileId() != null) p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("fileId"), request.getFileId()));
            if(request.getRecordStatus() != null) p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("recordStatus"), request.getRecordStatus()));
            if(request.getWorkOrderId() != null) p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("workOrderId"), request.getWorkOrderId()));
            if(request.getOrganization() != null) p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("organizationUnit"), request.getOrganization()));
            if(request.getPlace() != null) p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("place"), request.getPlace()));
            if(request.getServiceId() != null) p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("serviceId"), request.getServiceId()));
            if(request.getReferenceId() != null) p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("referenceId"), request.getReferenceId()));
            if(request.getRequestType() != null) p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("requestType"), request.getRequestType()));
            if(request.getUserId() != null) p = criteriaBuilder.and(p, criteriaBuilder.equal(root.get("userId"), request.getUserId()));

            return p;
        };
    }
}
