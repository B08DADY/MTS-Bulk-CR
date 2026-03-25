package com.mts.bulkvalidation.repository;

import com.mts.bulkvalidation.model.WfEmpRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WfEmpRoleRepository extends JpaRepository<WfEmpRole, String> {
}
