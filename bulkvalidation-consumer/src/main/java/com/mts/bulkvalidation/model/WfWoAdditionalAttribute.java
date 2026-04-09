package com.mts.bulkvalidation.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "WF_WO_ADDITIONAL_ATTRIBUTE", schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
public class WfWoAdditionalAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDITIONAL_ID", nullable = false)
    private Long additionalId;

    @Column(name = "WORK_ORDER_ID", length = 20)
    private String workOrderId;

    @Column(name = "SECTION_ID")
    private Long sectionId;

    @Column(name = "ATT_ID")
    private Long attId;

    @Column(name = "ATT_VALUE", length = 500)
    private String attValue;

}
