package com.mts.bulkhandling.repository;


import com.mts.bulkhandling.model.BsCfgReqType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BsCfgReqTypeRepository  extends JpaRepository<BsCfgReqType, String> {
    List<BsCfgReqType> findByRequestTypeDesc(String requestTypeDesc);

}
