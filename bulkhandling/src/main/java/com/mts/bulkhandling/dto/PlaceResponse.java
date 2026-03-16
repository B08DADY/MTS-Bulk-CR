package com.mts.bulkhandling.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponse {
    private String  id;
    private String placeName;

    public static PlaceResponse fromEntity(com.mts.bulkhandling.model.LcPlaceDemographic entity) {
        PlaceResponse placedto = new PlaceResponse();
        placedto.setId(entity.getId());
        placedto.setPlaceName(entity.getPlaceDesc());
        return placedto;
    }
}
