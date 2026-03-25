package com.mts.bulkvalidation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "WF_EMP_ROLE", schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
public class WfEmpRole {

    @Id
    @Column(name = "EMP_ROLE_ID", length = 50)
    private String empRoleId;
}
