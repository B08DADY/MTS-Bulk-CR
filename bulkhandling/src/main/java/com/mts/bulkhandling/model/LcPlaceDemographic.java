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
@Table(name = "LC_PLACE_DEMOGRAPHIC",schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LcPlaceDemographic {
    @Id
    @Column(name = "PLACE_ID")
    private String id;

    @Column(name = "PLACE_DESC")
    private String placeDesc;

    @Column(name = "ORG_ROLE_ID")
    private String orgRoleId;
}
