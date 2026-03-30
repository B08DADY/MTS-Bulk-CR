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
        try {
        return reqTypeRepository.findByRequestTypeDesc(FO_FTTH_DESC)
                .stream()
                .map(Mapper::toReqTypeResponse)
                .collect(Collectors.toList());
        } catch (org.springframework.dao.DataAccessException e) {
            throw new RuntimeException("Failed to retrieve request types due to a database error", e);

        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while retrieving request types", e);
        }
    }

}
