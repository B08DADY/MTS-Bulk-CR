package com.mts.bulkvalidation.repository;

import com.mts.bulkvalidation.model.LkpDeviceTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LkpDeviceTypesRepository extends JpaRepository<LkpDeviceTypes, String> {
    boolean existsByDeviceTypeCodeAndServiceTypeCode(String deviceTypeCode, String serviceTypeCode);
}
