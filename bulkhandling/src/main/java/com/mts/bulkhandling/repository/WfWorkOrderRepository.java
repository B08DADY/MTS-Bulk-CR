package com.mts.bulkhandling.repository;

import com.mts.bulkhandling.model.WfWorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WfWorkOrderRepository
        extends JpaRepository<WfWorkOrder, String>,
                JpaSpecificationExecutor<WfWorkOrder> {
}
