package com.javaweb.WebsiteRoomForRent.services;

import com.javaweb.WebsiteRoomForRent.dtos.BuildingDTO;
import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;
import com.javaweb.WebsiteRoomForRent.responses.BuildingSearchResponse;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> searchBuilding(BuildingSearchRequests buildingSearchRequests);
    BuildingDTO createBuilding(BuildingDTO buildingDTO);
    String deleteBuilding(Long id);
}
