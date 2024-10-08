package com.javaweb.WebsiteRoomForRent.services;

import com.javaweb.WebsiteRoomForRent.dtos.BuildingDTO;
import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;

import java.util.List;

public interface BuildingService {
    List<BuildingDTO> searchBuilding(BuildingSearchRequests buildingSearchRequests);
    BuildingDTO createOrUpdateBuilding(BuildingDTO buildingDTO);
    String deleteBuilding(Long id);
    BuildingEntity getBuildingById(Long buildingId);
}