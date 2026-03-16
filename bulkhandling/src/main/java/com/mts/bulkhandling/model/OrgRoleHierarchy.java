package com.mts.bulkhandling.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORG_ROLE_HIERARCHY",schema = "MTS_WFM_2017")
@org.hibernate.annotations.Immutable   // view is read-only
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrgRoleHierarchy {
    @Id
    @Column(name = "ORG_ROLE_NAME")
    private String orgRoleName;

    @Column(name = "PARENT_ORG_ROLE")
    private String parentOrgRole;

    @Column(name = "ROOT_ORG_ROLE")
    private String rootOrgRole;
}
