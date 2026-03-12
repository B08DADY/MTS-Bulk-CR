package com.mts.bulkhandling.repository;

import com.mts.bulkhandling.model.WfWoBulkCloseQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WfWoBulkCloseQueueRepository
        extends JpaRepository<WfWoBulkCloseQueue, Long> {
}
