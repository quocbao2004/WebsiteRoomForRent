package com.javaweb.WebsiteRoomForRent.repos.custom;

import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;

import java.util.List;

public interface BuildingRepoCustom {
    List<BuildingEntity> findAll(BuildingSearchRequests buildingSearchRequests);
}
