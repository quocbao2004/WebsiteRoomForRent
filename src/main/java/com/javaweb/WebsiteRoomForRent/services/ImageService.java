package com.javaweb.WebsiteRoomForRent.services;


import com.javaweb.WebsiteRoomForRent.dtos.ImageDTO;
import com.javaweb.WebsiteRoomForRent.entities.ImageEntity;

import java.io.File;
import java.util.List;

public interface ImageService {
    ImageEntity createBuildingImage(Long buildingId, ImageDTO imageDTO) throws Exception;
    File getBuildingImagesVids(String fileName) throws Exception;
    List<String> getBuildingImagesNames(Long buildingId);
    String deleteBuildingImage(String fileName);
}