package com.javaweb.WebsiteRoomForRent.services.impl;

import com.javaweb.WebsiteRoomForRent.converters.BuildingConverter;
import com.javaweb.WebsiteRoomForRent.dtos.BuildingDTO;
import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import com.javaweb.WebsiteRoomForRent.repos.BuildingRepo;
import com.javaweb.WebsiteRoomForRent.repos.UserRepo;
import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;
import com.javaweb.WebsiteRoomForRent.services.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepo buildingRepo;
    private final BuildingConverter buildingConverter;
    private final UserRepo userRepository;

    @Override
    public List<BuildingDTO> searchBuilding(BuildingSearchRequests buildingSearchRequests) {
        List<BuildingEntity> list = buildingRepo.findAll(buildingSearchRequests);
        List<BuildingDTO> res = list.stream().map((it -> buildingConverter.toDTO(it))).toList();
        return res;
    }

    @Override
    public BuildingDTO createOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity building = buildingConverter.toBuildingEntity(buildingDTO);
//        UserEntity user = userRepository.findById(1L).get();
//        building.setUserid(user);
        buildingRepo.save(building);
        return buildingDTO;
    }

    @Override
    public String deleteBuilding(Long id) {
        buildingRepo.deleteById(id);
        return "Delete Building " + id + " Successful";
    }
}
