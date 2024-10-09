package com.javaweb.WebsiteRoomForRent.converters;

import com.javaweb.WebsiteRoomForRent.dtos.ImageDTO;
import com.javaweb.WebsiteRoomForRent.entities.ImageEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageConverter {

    private final ModelMapper modelMapper;

    public ImageDTO toDTO(ImageEntity image) {
        ImageDTO imageDTO = modelMapper.map(image, ImageDTO.class);
        imageDTO.setBuildingId(image.getBuilding().getId());
        return imageDTO;
    }
}
