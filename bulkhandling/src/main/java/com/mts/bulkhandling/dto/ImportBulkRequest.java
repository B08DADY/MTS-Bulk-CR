package com.mts.bulkhandling.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportBulkRequest {

    @NotBlank(message = "workOrderId is required")
    private String workOrderId;

    @NotBlank(message = "referenceId is required")
    private String referenceId;

    @NotBlank(message = "serviceNumber is required")
    private String serviceNumber;

    @NotBlank(message = "organizationUnit is required")
    private String organizationUnit;

    @NotBlank(message = "workerId is required")
    private String workerId;

    @NotBlank(message = "closeCode is required")
    private String closeCode;
}