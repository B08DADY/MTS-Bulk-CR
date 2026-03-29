package com.mts.bulkvalidation.repository;

import com.mts.bulkvalidation.model.WfWoBulkQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WfWoBulkCloseQueueRepository extends JpaRepository<WfWoBulkQueue, Long>, JpaSpecificationExecutor<WfWoBulkQueue> {

    boolean existsByWorkOrderIdAndRecordStatusIn(String workOrderId, List<String> statuses);
    List<WfWoBulkQueue> findByRecordStatus(String recordStatus);

}
