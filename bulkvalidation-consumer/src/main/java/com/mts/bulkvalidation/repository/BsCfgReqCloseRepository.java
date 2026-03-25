package com.mts.bulkvalidation.repository;

import com.mts.bulkvalidation.model.BsCfgReqClose;
import com.mts.bulkvalidation.model.BsCfgReqCloseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BsCfgReqCloseRepository extends JpaRepository<BsCfgReqClose, BsCfgReqCloseId> {
}
