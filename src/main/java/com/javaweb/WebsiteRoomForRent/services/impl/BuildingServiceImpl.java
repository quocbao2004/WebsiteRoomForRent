package com.javaweb.WebsiteRoomForRent.services.impl;

import com.javaweb.WebsiteRoomForRent.dtos.BuildingDTO;
import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;
import com.javaweb.WebsiteRoomForRent.responses.BuildingSearchResponse;
import com.javaweb.WebsiteRoomForRent.services.BuildingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Override
    public List<BuildingSearchResponse> searchBuilding(BuildingSearchRequests buildingSearchRequests) {
        return List.of();
    }

    @Override
    public BuildingDTO createBuilding(BuildingDTO buildingDTO) {
        return null;
    }

    @Override
    public String deleteBuilding(Long id) {
        return "";
    }
}
