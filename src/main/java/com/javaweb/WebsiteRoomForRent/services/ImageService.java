package com.javaweb.WebsiteRoomForRent.services;


import com.javaweb.WebsiteRoomForRent.dtos.ImageDTO;
import com.javaweb.WebsiteRoomForRent.entities.ImageEntity;

import java.util.List;

public interface ImageService {
    ImageEntity createBuildingImage(Long buildingId, ImageDTO imageDTO) throws Exception;
    List<String> getBuildingImages(Long buildingId) throws Exception;
}