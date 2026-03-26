package com.mts.bulkhandling.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "WF_WO_BULK_CLOSE_QUEUE", schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WfWoBulkQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FILE_ID", nullable = false)
    private String fileId;

    @Column(name = "work_order_id", insertable = false, updatable = false)
    private String workOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_order_id")
    private WfWorkOrder workOrder;

    @Column(name = "ORGANIZATION_UNIT", length = 100)
    private String organizationUnit;

    @Column(name = "PLACE", length = 100)
    private String place;

    @Column(name = "SERVICE_ID", length = 20)
    private String serviceId;

    @Column(name = "REFERENCE_ID", length = 50)
    private String referenceId;

    @Column(name = "REQUEST_TYPE", length = 20)
    private String requestType;

    @Column(name = "USER_ID", length = 50)
    private String userId;

    @Column(name = "RECORD_STATUS", length = 50)
    private String recordStatus;

    @Column(name = "ORDER_STATUS", length = 50)
    private String orderStatus;

    @Column(name = "ACTION_DATE")
    private LocalDateTime actionDate;

    @Column(name = "WORKER_ID")
    private String workerId;

    @Column(name="CLOSE_CODE")
    private String closeCode;

    @Column(name="BOX")
    private String box;

    @Column(name="CABINET")
    private String cabinet;


    @Column(name="DEVICE_TYPE")
    private String deviceType;


    @Column(name="SERIAL_NUMBER")
    private String serialNumber;

    @Column(name="MAC_ADDRESS")
    private String macAddress;

    @Column(name="FAIL_REASON")
    private String failReason;





}
