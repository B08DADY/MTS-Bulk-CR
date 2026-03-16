package com.mts.bulkhandling.dto;

import com.mts.bulkhandling.model.BsCfgReqType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqTypeResponse {
    private  String requestType;

    public static ReqTypeResponse fromEntity(BsCfgReqType entity){
        ReqTypeResponse requestTypedto = new ReqTypeResponse();
        requestTypedto.setRequestType(entity.getRequestType());
        return  requestTypedto;

    }
}
