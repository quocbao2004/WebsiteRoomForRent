package com.javaweb.WebsiteRoomForRent.repos;

import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.repos.custom.BuildingRepoCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepo extends JpaRepository<BuildingEntity, Long>, BuildingRepoCustom {
}
