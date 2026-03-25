package com.mts.bulkhandling.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "LKP_DEVICE_TYPES", schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LkpDeviceTypes {

    // -------------------------------------------------------------------------
    // Primary Key
    // -------------------------------------------------------------------------
    @Id
    @Column(name = "DEVICE_TYPE_CODE", length = 50, nullable = false)
    private String deviceTypeCode;

    // -------------------------------------------------------------------------
    // Columns
    // -------------------------------------------------------------------------
    @Column(name = "DEVICE_TYPE_NAME_AR", length = 50)
    private String deviceTypeNameAr;

    @Column(name = "DEVICE_TYPE_NAME_EN", length = 50)
    private String deviceTypeNameEn;

    @Column(name = "SERVICE_TYPE_CODE", length = 200)
    private String serviceTypeCode;
}