package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.OrgRoleResponse;
import com.mts.bulkhandling.mapper.Mapper;
import com.mts.bulkhandling.repository.OrgRoleHierarchyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrgRoleService {
    @Autowired
    private OrgRoleHierarchyRepository orgRoleHierarchyRepository;


    public List<OrgRoleResponse> getOrgHierarchy(String orgRoleName) {
        if (orgRoleName == null || orgRoleName.isEmpty()) {
            throw new IllegalArgumentException("Org role name must not be null or empty");
        }
        try {
        return orgRoleHierarchyRepository.findByRootOrgRole(orgRoleName)
                .stream()
                .map(Mapper::toOrgRoleResponse)
                .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw e;

        } catch (org.springframework.dao.DataAccessException e) {
            throw new RuntimeException("Failed to retrieve org hierarchy due to a database error", e);

        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while retrieving org hierarchy", e);
        }
    }
}
