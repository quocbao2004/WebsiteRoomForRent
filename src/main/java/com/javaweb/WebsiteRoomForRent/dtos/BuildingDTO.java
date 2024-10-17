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

    @NotNull(message = "Building's managerName can't be null")
    @NotBlank(message = "Building's managerName can't be blank")
    private String managerName;

    @NotNull(message = "Building's managerphone can't be null")
    @NotBlank(message = "Building's managerphone can't be blank")
    private String managerphone;

    @NotNull(message = "Building's rentPrice can't be null")
    private Long rentPrice;

    @NotNull(message = "Building's servicefee can't be null")
    private Long servicefee;

    @NotNull(message = "Building's carfee can't be null")
    private Long carfee;

    @NotNull(message = "Building's motofee can't be null")
    private  Long motofee;

    @NotNull(message = "Building's waterfee can't be null")
    private Long waterfee;

    @NotNull(message = "Building's electricityfee can't be null")
    private Long electricityfee;

    @NotNull(message = "Building's deposit can't be null")
    @NotBlank(message = "Building's deposit can't be blank")
    private String deposit;

    @NotNull(message = "Building's number of available rooms can't be null")
    private Long totalNumberOfAvailableRooms;

    private String description;
}