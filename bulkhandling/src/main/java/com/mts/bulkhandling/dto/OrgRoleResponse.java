package com.mts.bulkhandling.dto;

import com.mts.bulkhandling.model.OrgRoleHierarchy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgRoleResponse {
    private String orgRoleName;

    public static OrgRoleResponse fromEntity(OrgRoleHierarchy entity) {
        OrgRoleResponse dto = new OrgRoleResponse();
        dto.setOrgRoleName(entity.getOrgRoleName());
        return dto;
    }
}
