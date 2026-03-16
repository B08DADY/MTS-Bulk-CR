package com.mts.bulkhandling.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BS_CFG_REQ_TYPE",schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BsCfgReqType {
    @Id()
    @Column(name = "REQUEST_TYPE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String requestType;

    @Column(name = "REQUEST_TYPE_DESC")
    private String requestTypeDesc;


    @Column(name = "BULK_REQ_CATEGORY")
    private String bulkReqCategory;

}
