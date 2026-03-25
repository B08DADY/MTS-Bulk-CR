package com.mts.bulkhandling.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class ImportRetailSuccessBulkRequest extends ImportBulkRequest {

//    @NotBlank(message = "serialNumber is required")
    private String serialNumber;

    private String macAddress;

//    @NotBlank(message = "deviceType is required")
    private String deviceType;

}
