package com.javaweb.WebsiteRoomForRent.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO {

    private Long id;

    private String name;

    private String ward;

    private String type;

    private String district;

    private String street;

    private Long floorArea;

    private String managerName;

    private String managerphone;

    private Long rentPrice;

    private Long servicefee;

    private Long carfee;

    private  Long motofee;

    private Long waterfee;

    private Long electricityfee;

    private String deposit;

    private Long totalNumberOfAvailableRooms;

    private String description;
}