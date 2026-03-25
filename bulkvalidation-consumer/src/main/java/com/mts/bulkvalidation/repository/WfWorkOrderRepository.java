package com.mts.bulkvalidation.repository;

import com.mts.bulkvalidation.model.WfWorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WfWorkOrderRepository
        extends JpaRepository<WfWorkOrder, String>,
                JpaSpecificationExecutor<WfWorkOrder> {
}
