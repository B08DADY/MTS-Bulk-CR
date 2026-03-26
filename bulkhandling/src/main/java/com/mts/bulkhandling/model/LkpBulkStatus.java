package com.mts.bulkhandling.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LKP_BULK_STATUS", schema = "MTS_WFM_2017")
public class LkpBulkStatus {

    @Id
    @Column(name = "STATUS")
    private String status;

}
