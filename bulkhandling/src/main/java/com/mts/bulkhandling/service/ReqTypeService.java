package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.ReqTypeResponse;
import com.mts.bulkhandling.mapper.Mapper;
import com.mts.bulkhandling.repository.BsCfgReqTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReqTypeService {
    private static final String FO_FTTH_DESC = "FO-FTTH";

    private final BsCfgReqTypeRepository reqTypeRepository;

    public ReqTypeService(BsCfgReqTypeRepository reqTypeRepository) {
        this.reqTypeRepository = reqTypeRepository;
    }

    /**
     * Retrieves all request types where REQUEST_TYPE_DESC = 'FO-FTTH'
     * and maps them to response DTOs.
     *
     * @return list of matching request types
     */
    public List<ReqTypeResponse> getFoFtthRequestTypes() {
        return reqTypeRepository.findByRequestTypeDesc(FO_FTTH_DESC)
                .stream()
                .map(Mapper::toReqTypeResponse)
                .collect(Collectors.toList());
    }

}
