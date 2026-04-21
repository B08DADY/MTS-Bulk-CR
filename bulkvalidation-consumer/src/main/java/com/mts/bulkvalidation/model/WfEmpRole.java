package com.mts.bulkvalidation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WF_EMP_ROLE", schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
public class WfEmpRole {

    @Id
    @Column(name = "EMP_ROLE_ID", length = 20, nullable = false)
    private String empRoleId;

    @OneToMany(mappedBy = "empRole", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WfEmpRoleZone> zones;

    @OneToMany(mappedBy = "empRole", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WfEmpRoleReqType> requestTypes;
}