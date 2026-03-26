package com.mts.bulkhandling.repository;

import com.mts.bulkhandling.model.LkpBulkStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LkpBulkStatusRepository extends JpaRepository<LkpBulkStatus, String> {
}
