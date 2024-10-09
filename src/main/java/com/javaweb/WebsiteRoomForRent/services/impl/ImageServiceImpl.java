package com.javaweb.WebsiteRoomForRent.services.impl;

import com.javaweb.WebsiteRoomForRent.customexceptions.DataNotFoundException;
import com.javaweb.WebsiteRoomForRent.dtos.ImageDTO;
import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.entities.ImageEntity;
import com.javaweb.WebsiteRoomForRent.repository.BuildingRepository;
import com.javaweb.WebsiteRoomForRent.repository.ImageRepository;
import com.javaweb.WebsiteRoomForRent.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final BuildingRepository buildingRepository;

    @Override
    public ImageEntity createBuildingImage(Long buildingId, ImageDTO imageDTO) {
        BuildingEntity existingBuilding = buildingRepository.findById(buildingId)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Cannot find product with id: "+ imageDTO.getBuildingId()));
        ImageEntity imageEntity = ImageEntity.builder()
                .building(existingBuilding)
                .imageUrl(imageDTO.getImageUrl())
                .build();
        return imageRepository.save(imageEntity);
    }

    @Override
    public List<String> getBuildingImagesVids(Long buildingId) throws Exception {
        return imageRepository.findByBuildingId(buildingId).stream().map(it->it.getImageUrl()).toList();
    }
}