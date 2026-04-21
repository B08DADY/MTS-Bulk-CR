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
public class WfEmpRoleZoneId implements Serializable {

    @Column(name = "EMP_ROLE_ID", length = 20, nullable = false)
    private String empRoleId;

    @Column(name = "ZONE_ID", nullable = false)
    private Long zoneId;
}