package com.mts.bulkvalidation.repository;

import com.mts.bulkvalidation.model.WfWoBulkQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WfWoBulkCloseQueueRepository
        extends JpaRepository<WfWoBulkQueue, Long>, JpaSpecificationExecutor<WfWoBulkQueue> {
}
