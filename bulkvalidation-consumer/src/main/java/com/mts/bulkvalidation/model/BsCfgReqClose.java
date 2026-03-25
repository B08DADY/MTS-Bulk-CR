package com.mts.bulkvalidation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BS_CFG_REQ_CLOSE", schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BsCfgReqClose {

    @EmbeddedId
    private BsCfgReqCloseId id;

    @Column(name = "CLOSE_NAME", length = 100, nullable = false)
    private String closeName;

    @Column(name = "CATEGORY")
    private Integer category;

    @Column(name = "OUTAGE")
    private Integer outage = 0;

    @Column(name = "ALLOW_REOPEN")
    private Integer allowReopen = 1;

    @Column(name = "CLOSE_TYPE", length = 50)
    private String closeType;

    @PrePersist
    @PreUpdate
    private void trimCloseName() {
        if (this.closeName != null) {
            this.closeName = this.closeName.trim();
        }
    }
}
