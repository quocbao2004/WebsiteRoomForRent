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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final BuildingRepository buildingRepository;

    public static final String STORAGE_DIRECTORY = "D:\\Workspace\\Project\\WebsieRoomForRent_NguyenKhang\\WebsiteRoomForRent\\images";

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
    public File getBuildingImagesVids(String fileName) throws Exception {
        if (fileName == null) throw new NullPointerException("file name is null");
        File fileToDownload = new File(STORAGE_DIRECTORY + File.separator + fileName);
        if (!Objects.equals(fileToDownload.getParent(), STORAGE_DIRECTORY)) throw new SecurityException("Unsupported filename!");
        if (!fileToDownload.exists()) throw new FileNotFoundException("No file named: " + fileName);
        return fileToDownload;
    }

    @Override
    public List<String> getBuildingImagesNames(Long buildingId) {
        return imageRepository.findByBuildingId(buildingId).stream().map(it -> it.getImageUrl()).toList();
    }

    @Override
    public String deleteBuildingImage(String fileName) {
        imageRepository.deleteByImageUrl(fileName);
        File file = new File(STORAGE_DIRECTORY + File.separator + fileName);
        if(file.delete()) {
            return "Image deleted successfully";
        }
        else return "Image could not be deleted";
    }
}