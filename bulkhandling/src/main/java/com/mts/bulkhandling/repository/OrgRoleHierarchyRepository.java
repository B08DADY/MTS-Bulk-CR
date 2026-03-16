package com.mts.bulkhandling.repository;

import com.mts.bulkhandling.model.OrgRoleHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgRoleHierarchyRepository extends JpaRepository<OrgRoleHierarchy, String> {


    List<OrgRoleHierarchy> findByRootOrgRole(String rootOrgRole);
}