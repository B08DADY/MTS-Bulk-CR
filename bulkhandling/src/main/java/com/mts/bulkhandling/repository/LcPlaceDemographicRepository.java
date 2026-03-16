package com.mts.bulkhandling.repository;

import com.mts.bulkhandling.model.LcPlaceDemographic;
import com.mts.bulkhandling.model.OrgRoleHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LcPlaceDemographicRepository extends JpaRepository<LcPlaceDemographic, Long> {


    List<LcPlaceDemographic> findByOrgRoleId(String orgRoleId);
}