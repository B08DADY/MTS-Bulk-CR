package com.mts.bulkhandling.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkOrderSearchRequest {

    // ── Filters from WF_WORK_ORDER ──────────────────────────────────────────
    private String workOrderId;
    private String organization;   // maps to ORG_ROLE_NAME
    private String place;          // maps to PLACE_ID
    private String serviceId;      // maps to SERVICE_ID
    private String referenceId;    // maps to REFERENCE_ID
    private String requestType;    // maps to REQUEST_TYPE
    private String bulkStatus;
    // ── Filters from WF_WO_BULK_CLOSE_QUEUE ────────────────────────────────
  //  private Long   fileId;         // maps to FILE_ID
   // private String recordStatus;   // maps to RECORD_STATUS (must still exclude 'pending validation')

    // ── Pagination / Sorting ────────────────────────────────────────────────
    private int    page    = 0;
    private int    size    = 10;
    private String sortBy  = "workOrderId";
    private String sortDir = "asc";
}
