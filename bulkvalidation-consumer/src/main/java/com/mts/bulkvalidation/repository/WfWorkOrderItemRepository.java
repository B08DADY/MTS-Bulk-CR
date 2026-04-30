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

    @Query(value = "SELECT w.work_id AS \"workId\", w.instance_id AS \"instanceId\", w.accept_flag AS \"acceptFlag\", w.status AS \"status\" " +
            "FROM wf_work_order_item woi " +
            "JOIN wf_work w ON w.work_id = woi.work_id " +
            "JOIN bs_cfg_req_type_items bs ON woi.wo_item_sequence = bs.req_item_seq " +
            "WHERE woi.work_order_id = :workOrderId " +
            "AND bs.request_type = :requestType " +
            "AND bs.bulk_flag = 1 " +
            "ORDER BY w.work_id ASC", nativeQuery = true)
    List<WorkInstanceProjection> findOrderTasks(@Param("workOrderId") String workOrderId,
                                                @Param("requestType") String requestType);





    @Query(value = "SELECT w.work_id AS \"workId\", w.instance_id AS \"instanceId\", w.accept_flag AS \"acceptFlag\", w.status AS \"status\" " +
            "FROM wf_work_order_item woi " +
            "JOIN wf_work w ON w.work_id = woi.work_id " +
            "JOIN bs_cfg_req_type_items bs ON woi.wo_item_sequence = bs.req_item_seq " +
            "WHERE woi.work_order_id = :workOrderId " +
            "AND bs.request_type = :requestType " +
            "AND w.status IN ('Pending', 'Dispatched','Assigned','Started','Pending Processing') " +
            "ORDER BY w.work_id DESC", nativeQuery = true)
    List<WorkInstanceProjection> findLastPendingOrDispatchedWork(@Param("workOrderId") String workOrderId, @Param("requestType") String requestType);

    // ── RETAIL: last work where accept_flag = 0 (no bulk_flag) ────────────────
    @Query(value = "SELECT w.work_id AS \"workId\", w.instance_id AS \"instanceId\", w.accept_flag AS \"acceptFlag\", w.status AS \"status\" " +
            "FROM wf_work_order_item woi " +
            "JOIN wf_work w ON w.work_id = woi.work_id " +
            "JOIN bs_cfg_req_type_items bs ON woi.wo_item_sequence = bs.req_item_seq " +
            "WHERE woi.work_order_id = :workOrderId " +
            "AND bs.request_type = :requestType " +
            "AND w.accept_flag = 0 " +
            "ORDER BY w.work_id DESC", nativeQuery = true)
    List<WorkInstanceProjection> findLastAcceptablWork(@Param("workOrderId") String workOrderId,
                                                       @Param("requestType") String requestType);






}
