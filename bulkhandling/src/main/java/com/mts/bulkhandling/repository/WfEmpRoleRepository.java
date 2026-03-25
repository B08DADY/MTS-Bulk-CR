package com.mts.bulkhandling.repository;

import com.mts.bulkhandling.model.WfEmpRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WfEmpRoleRepository extends JpaRepository<WfEmpRole, String> {
}
