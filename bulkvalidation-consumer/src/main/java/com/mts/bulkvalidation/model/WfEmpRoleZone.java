package com.mts.bulkvalidation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "WF_EMP_ROLE_ZONE", schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
public class WfEmpRoleZone {

    @EmbeddedId
    private WfEmpRoleZoneId id;

    @Column(name = "ORG_ROLE", length = 50)
    private String orgRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empRoleId")
    @JoinColumn(name = "EMP_ROLE_ID", referencedColumnName = "EMP_ROLE_ID")
    private WfEmpRole empRole;
}