package com.javaweb.WebsiteRoomForRent.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingSearchResponse {

    private Long id;
    private String name;
    private String ward;
    private String type;
    private String district;
    private String street;
    private float floorArea;
    private String managerName;
    private String managerphone;
    private float rentPrice;
    private float servicefee;
    private float carfee;
    private  float motofee;
    private float waterfee;
    private float electricityfee;
    private String deposit;
    private Long totalNumberOfAvailableRooms;
    private String description;
    private List<String> images;
}