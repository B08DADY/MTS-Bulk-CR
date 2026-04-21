package com.mts.bulkvalidation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "WF_EMP_ROLE_REQ_TYPE", schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
public class WfEmpRoleReqType {

    @EmbeddedId
    private WfEmpRoleReqTypeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empRole")
    @JoinColumn(name = "EMP_ROLE", referencedColumnName = "EMP_ROLE_ID")
    private WfEmpRole empRole;
}