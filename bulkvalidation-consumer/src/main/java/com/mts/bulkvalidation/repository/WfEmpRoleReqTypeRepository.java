package com.mts.bulkvalidation.repository;

import com.mts.bulkvalidation.model.WfEmpRoleReqType;
import com.mts.bulkvalidation.model.WfEmpRoleReqTypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WfEmpRoleReqTypeRepository extends JpaRepository<WfEmpRoleReqType, WfEmpRoleReqTypeId> {

}
