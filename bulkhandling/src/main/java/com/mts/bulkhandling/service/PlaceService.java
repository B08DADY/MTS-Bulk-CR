package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.PlaceResponse;
import com.mts.bulkhandling.repository.LcPlaceDemographicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceService {
    private final LcPlaceDemographicRepository placeRepository;

    public PlaceService(LcPlaceDemographicRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    /**
     * Retrieves all places associated with the given organization name.
     *
     * @param organizationName the ORG_ROLE_ID to filter by
     * @return list of matching places (id + placeName only)
     */
    public List<PlaceResponse> getPlacesByOrganization(String organizationName) {
        return placeRepository.findByOrgRoleId(organizationName)
                .stream()
                .map(PlaceResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

