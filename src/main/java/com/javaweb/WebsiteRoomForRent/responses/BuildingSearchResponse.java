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
    private List<String> images;
}
