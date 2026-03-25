package com.mts.bulkhandling.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class ImportFoBulkRequest extends ImportBulkRequest{


    private String cabinet ;

    private String box ;
}
