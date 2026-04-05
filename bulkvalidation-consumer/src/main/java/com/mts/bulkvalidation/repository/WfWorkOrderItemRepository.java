package com.mts.bulkvalidation.repository;

import com.mts.bulkvalidation.model.WfWorkOrderItem;
import com.mts.bulkvalidation.repository.projection.WorkInstanceProjection;
import org.springframework.data.domain.Pageable;          // ✅ Add this
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WfWorkOrderItemRepository extends JpaRepository<WfWorkOrderItem, Long> {


    @Query("SELECT woi.work.workId AS workId, woi.work.instanceId AS instanceId " +
            "FROM WfWorkOrderItem woi " +
            "WHERE woi.workOrderId = :workOrderId " +
            "AND woi.work.status = 'Started' " +
            "ORDER BY woi.updatedDate ASC, woi.work.workId DESC")
    List<WorkInstanceProjection> findTopStartedWork(@Param("workOrderId") String workOrderId,
                                                    Pageable pageable);
}
