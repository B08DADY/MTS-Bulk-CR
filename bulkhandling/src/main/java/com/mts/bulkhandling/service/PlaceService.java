package com.mts.bulkhandling.service;

import com.mts.bulkhandling.dto.PlaceResponse;
import com.mts.bulkhandling.mapper.Mapper;
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
        if (organizationName == null || organizationName.isEmpty()) {
            throw new IllegalArgumentException("Organization name must not be null or empty");
        }
        try {
        return placeRepository.findByOrgRoleId(organizationName)
                .stream()
                .map(Mapper::toPlaceResponse)
                .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw e;

        } catch (org.springframework.dao.DataAccessException e) {
            throw new RuntimeException("Failed to retrieve places due to a database error", e);

        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while retrieving places", e);
        }
    }
}
