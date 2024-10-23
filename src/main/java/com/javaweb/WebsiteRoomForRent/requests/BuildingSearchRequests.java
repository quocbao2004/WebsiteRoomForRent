package com.javaweb.WebsiteRoomForRent.requests;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuildingSearchRequests {

    private Long id;
    private String name;
    private String ward;
    private List<String> type;
    private String district;
    private String street;
    private Long floorAreaFrom;
    private Long floorAreaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
}