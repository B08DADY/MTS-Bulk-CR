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
        return orgRoleHierarchyRepository.findByRootOrgRole(orgRoleName)
                .stream()
                .map(Mapper::toOrgRoleResponse)
                .collect(Collectors.toList());
    }
}
