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

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotNull(message = "Ward can't be null")
    @NotBlank(message = "Ward can't be blank")
    private String ward;

    @NotNull(message = "Building's type can't be null")
    @NotBlank(message = "Building's type can't be blank")
    private String type;

    @NotNull(message = "Building's district can't be null")
    @NotBlank(message = "Building's district can't be blank")
    private String district;

    @NotNull(message = "Building's street can't be null")
    @NotBlank(message = "Building's street can't be blank")
    private String street;

    @NotNull(message = "Building's floor area can't be null")
    private Long floorArea;
    private String managerName;
    private String managerphone;
    private Float rentPrice;
    private Float servicefee;
    private Float carfee;
    private  Float motofee;
    private Float waterfee;
    private Float electricityfee;
    private String deposit;
    private Long totalNumberOfAvailableRooms;
    private String description;
}