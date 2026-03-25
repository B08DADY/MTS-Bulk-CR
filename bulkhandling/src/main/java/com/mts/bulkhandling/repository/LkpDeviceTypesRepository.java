package com.mts.bulkhandling.repository;

import com.mts.bulkhandling.model.LkpDeviceTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LkpDeviceTypesRepository extends JpaRepository<LkpDeviceTypes, String> {
    boolean existsByDeviceTypeCodeAndServiceTypeCode(String deviceTypeCode, String serviceTypeCode);

}