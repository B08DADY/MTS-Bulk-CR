package com.mts.bulkhandling.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "WF_WORK_ORDER", schema = "MTS_WFM_2017")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WfWorkOrder {

    @Id
    @Column(name = "WORK_ORDER_ID", length = 20, nullable = false)
    private String workOrderId;

    @Column(name = "REQUESTED_DELIVERY_DATE")
    @Temporal(TemporalType.DATE)
    private Date requestedDeliveryDate;

    @Column(name = "POSSIBLE_DELIVERY_DATE")
    @Temporal(TemporalType.DATE)
    private Date possibleDeliveryDate;

    @Column(name = "ORG_ROLE_NAME", length = 50)
    private String orgRoleName;


    @Column(name = "BULK_STATUS", length = 50)
    private String bulkStatus;

    @Column(name = "WORK_ORDER_DESC", length = 4000)
    private String workOrderDesc;

    @Column(name = "INITATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date initiateDate;

    @Column(name = "CLOSED_DATE")
    @Temporal(TemporalType.DATE)
    private Date closedDate;

    @Column(name = "WO_STATUS", length = 50)
    private String woStatus;

    @Column(name = "REQUEST_TYPE", length = 20)
    private String requestType;

    @Column(name = "SERVICE_ID", length = 20)
    private String serviceId;

    @Column(name = "RESOURCE_ID", length = 20)
    private String resourceId;

    @Column(name = "PRODUCT_ID", length = 20)
    private String productId;

    @Column(name = "CUSTOMER_ID", length = 20)
    private String customerId;

    @Column(name = "WO_PRIORITY", length = 20, nullable = false)
    private String woPriority;

    @Column(name = "WO_STAGE", length = 20, nullable = false)
    private String woStage;

    @Column(name = "LONGITUDE", precision = 10, scale = 7)
    private BigDecimal longitude;

    @Column(name = "LATITUDE", precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(name = "WO_ADDRESS", length = 200)
    private String woAddress;

    @Column(name = "PLACE_ID", length = 20)
    private String placeId;

    @Column(name = "APPOINTEMNET_ID")
    private Long appointemnetId;

    @Column(name = "CLOSE_CODE", length = 20)
    private String closeCode;

    @Column(name = "CLOSED_BY", length = 50)
    private String closedBy;

    @Column(name = "NOTES", length = 1000)
    private String notes;

    @Column(name = "COLUMN1", length = 20)
    private String column1;

    @Column(name = "FCC_FLAG")
    private Long fccFlag;

    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Column(name = "INSTANCE_ID")
    private Long instanceId;

    @Column(name = "REFERENCE_ID", length = 50)
    private String referenceId;

    @Column(name = "INITIATOR", length = 50)
    private String initiator;

    @Column(name = "CATEGORY")
    private Long category;

    @Column(name = "RE_OPEN_CNT")
    private Long reOpenCnt;

    @Column(name = "CITY_CODE", length = 20)
    private String cityCode;

    @Column(name = "TEL_NO", length = 20)
    private String telNo;

    @Column(name = "EXPORT_FLAG")
    private Long exportFlag;

    @Column(name = "CHANGE_PLACE_FLAG")
    private Long changePlaceFlag;

    @Column(name = "CLOSE_REASON", length = 200)
    private String closeReason;

    @Column(name = "COM_ORDER_ID", length = 30)
    private String comOrderId;

    @Column(name = "COM_ORDER_TYPE", length = 30)
    private String comOrderType;

    @Column(name = "DEP_WO_CNT")
    private Long depWoCnt;

    @Column(name = "FILE_ID")
    private Long fileId;

    @Column(name = "PLACE_NAME", length = 200)
    private String placeName;

    @Column(name = "REMAINING_SLA_TIME")
    private Long remainingSlaTime;

    @Column(name = "REQUEST_TYPE_AR", length = 200)
    private String requestTypeAr;

    @Column(name = "REQUEST_TYPE_EN", length = 200)
    private String requestTypeEn;

    @Column(name = "SERVICE_TYPE", length = 200)
    private String serviceType;

    @Column(name = "SLA_DURATION")
    private Long slaDuration;

    @Column(name = "SLA_PAUSE_FLAG")
    private Long slaPauseFlag;

    @Column(name = "SLA_RESUME_DATE")
    @Temporal(TemporalType.DATE)
    private Date slaResumeDate;

    @Column(name = "SMS_COUNT")
    private Long smsCount;

    @Column(name = "SMS_FLAG")
    private Long smsFlag;

    @Column(name = "WO_PRIORITY_AR", length = 200)
    private String woPriorityAr;

    @Column(name = "ZONE_ID", length = 20)
    private String zoneId;

    @Column(name = "ZONE_NAME", length = 200)
    private String zoneName;

    @Column(name = "APPOINTMENT_START")
    @Temporal(TemporalType.DATE)
    private Date appointmentStart;

    @Column(name = "COMPOUND_NAME", length = 100)
    private String compoundName;

    @Column(name = "ZONE", length = 50)
    private String zone;

    @Column(name = "SECTOR", length = 50)
    private String sector;

    @Column(name = "CURRENT_WORKSPEC", length = 100)
    private String currentWorkspec;

    @Column(name = "LAST_TRANS_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastTransDate;

    @Column(name = "ISP_NAME", length = 500)
    private String ispName;

    @Column(name = "CURRENT_ACTION", length = 100)
    private String currentAction;

    @Column(name = "CURRENT_ACCEPT_FLAG")
    private Long currentAcceptFlag;

    @Column(name = "CURRENT_OWNER", length = 400)
    private String currentOwner;

    @Column(name = "PRIORITY_INDEX")
    private Long priorityIndex;

    @Column(name = "CURRENT_WORKER", length = 500)
    private String currentWorker;

    @Column(name = "TECHNOLOGY_TYPE", length = 200)
    private String technologyType;

    @Column(name = "PLACE_DESC", length = 200)
    private String placeDesc;

    @Column(name = "TEAM_LEADER_FLAG")
    private Long teamLeaderFlag;

    @Column(name = "APPOINTMENT_END")
    @Temporal(TemporalType.DATE)
    private Date appointmentEnd;

    @Column(name = "APP_PREFERRED_TIME", length = 5)
    private String appPreferredTime;

    @Column(name = "PONR_WITHDRAW")
    private Long ponrWithdraw;

    @Column(name = "PONR_MODIFY")
    private Long ponrModify;

    @Column(name = "PONR_CLOSE")
    private Long ponrClose;

    @Column(name = "PONR_FEEDBACK")
    private Long ponrFeedback;

    @Column(name = "OUTAGE_ID", length = 20)
    private String outageId;

    @Column(name = "CALL_TRIALS")
    private Long callTrials;

    @Column(name = "CLOSED_BY_ACTUAL", length = 50)
    private String closedByActual;

    @Column(name = "ACCEPT_DATE")
    @Temporal(TemporalType.DATE)
    private Date acceptDate;

    @Column(name = "ACCEPTED_FROM", length = 50)
    private String acceptedFrom;

    @Column(name = "CRITICAL_FLAG")
    private Long criticalFlag;

    @Column(name = "CALL_BRIDGE_FLAG", precision = 1, scale = 0)
    private BigDecimal callBridgeFlag;

    @Column(name = "DETRACTOR_FLAG", length = 20)
    private String detractorFlag;

    @Column(name = "SCHEDULING_STATUS", length = 20)
    private String schedulingStatus;

    @Column(name = "POP_NAME", length = 50)
    private String popName;

    @Column(name = "PONR_RESCHEDULE_FLAG")
    private Long ponrRescheduleFlag;

    @Column(name = "REQUEST_CATEGORY", length = 50)
    private String requestCategory;

    @Column(name = "SCH_SOURCE", length = 50)
    private String schSource;

    @Column(name = "CLOSE_REOPEN_DURATION")
    private Long closeReopenDuration;

    @Column(name = "LAST_ASSIGNED_BY", length = 50)
    private String lastAssignedBy;

    @Column(name = "ORIGINAL_SCH_START")
    @Temporal(TemporalType.DATE)
    private Date originalSchStart;

    @Column(name = "ORIGINAL_SCH_END")
    @Temporal(TemporalType.DATE)
    private Date originalSchEnd;

    @Column(name = "RESCHEDULE_COUNT")
    private Long rescheduleCount;

    @Column(name = "FVNO_FLAG", length = 20)
    private String fvnoFlag;

    @Column(name = "RESCHEDULED_BY", length = 200)
    private String rescheduledBy;

    @Column(name = "PREFERRED_TIME_FROM", length = 50)
    private String preferredTimeFrom;

    @Column(name = "PREFERRED_TIME_TO", length = 50)
    private String preferredTimeTo;

    @Column(name = "RESCH_NEW_PONR")
    private Long reschNewPonr;

    @Column(name = "CUST_PREFERRED_DATE")
    @Temporal(TemporalType.DATE)
    private Date custPreferredDate;

    @Column(name = "ORIGINAL_SCH_SOURCE", length = 50)
    private String originalSchSource;

    @Column(name = "MANUAL_SCHD_TIME")
    @Temporal(TemporalType.DATE)
    private Date manualSchdTime;

    @OneToMany(mappedBy = "workOrder", fetch = FetchType.LAZY)
    private List<WfWoBulkQueue> bulkQueue;
}
