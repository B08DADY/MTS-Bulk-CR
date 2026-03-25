package com.mts.bulkhandling.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.*;

@Entity
@Table(name = "WF_EMP_ROLE",schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WfEmpRole {
    @Id
    @Column(name = "EMP_ROLE_ID", length = 20, nullable = false)
    private String empRoleId;
}
