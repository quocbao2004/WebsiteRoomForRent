package com.javaweb.WebsiteRoomForRent.repository.custom;

import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingSearchRequests buildingSearchRequests);
}
