package com.mts.bulkvalidation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "WF_WORK_ORDER_ITEM")
@Getter
@Setter
@NoArgsConstructor
public class WfWorkOrderItem {

    @Id
    @Column(name = "WORK_ORDER_ITEM_ID")
    private Long workOrderItemId;

    @Column(name = "WORK_ORDER_ID")
    private String workOrderId;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORK_ID")
    private WfWork work;

}