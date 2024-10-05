package com.javaweb.WebsiteRoomForRent.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO {

    @NotNull(message = "Building's id can't be null")
    private Long id;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String name;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String ward;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String type;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String district;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String street;

    @NotNull(message = "Building's name can't be null")
    private Long floorArea;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String managerName;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String managerphone;

    @NotNull(message = "Building's name can't be null")
    private Long rentPrice;

    @NotNull(message = "Building's name can't be null")
    private Long servicefee;

    @NotNull(message = "Building's name can't be null")
    private Long carfee;

    @NotNull(message = "Building's name can't be null")
    private  Long motofee;

    @NotNull(message = "Building's name can't be null")
    private Long waterfee;

    @NotNull(message = "Building's name can't be null")
    private Long electricityfee;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String deposit;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String payment;

    @NotNull(message = "Building's name can't be null")
    private Long TotalNumberOfAvailableRooms;
}
