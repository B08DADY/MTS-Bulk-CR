package com.mts.bulkvalidation.repository;


import com.mts.bulkvalidation.model.WfEmpRoleZone;
import com.mts.bulkvalidation.model.WfEmpRoleZoneId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WfEmpRoleZoneRepository extends JpaRepository<WfEmpRoleZone, WfEmpRoleZoneId> {
}
