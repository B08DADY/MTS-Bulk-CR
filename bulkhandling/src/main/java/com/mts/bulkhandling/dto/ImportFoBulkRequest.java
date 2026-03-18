package com.mts.bulkhandling.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class ImportFoBulkRequest extends ImportBulkRequest{


    @NotBlank(message = "cabinet is required")
    private String cabinet ;

    @NotBlank(message = "box is required")
    private String box ;
}
