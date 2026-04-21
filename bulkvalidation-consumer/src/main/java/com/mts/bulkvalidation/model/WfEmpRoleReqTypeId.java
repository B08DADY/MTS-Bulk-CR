package com.mts.bulkvalidation.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WfEmpRoleReqTypeId implements Serializable {

    @Column(name = "EMP_ROLE", length = 20, nullable = false)
    private String empRole;

    @Column(name = "REQUEST_TYPE", length = 100, nullable = false)
    private String requestType;
}