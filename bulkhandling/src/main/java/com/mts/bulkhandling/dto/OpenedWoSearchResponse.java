package com.mts.bulkhandling.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenedWoSearchResponse {

    // ── From WF_WORK_ORDER ─────────────────────────────────────────────────
    private String workOrderId;
    private String orgRoleName;
    private String placeId;
    private String serviceId;
    private String referenceId;
    private String requestType;
    private String requestTypeEn;
    private String bulkStatus;
}
