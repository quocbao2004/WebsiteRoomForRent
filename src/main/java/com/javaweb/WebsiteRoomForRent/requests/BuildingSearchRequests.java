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
    private Float floorAreaFrom;
    private Float floorAreaTo;
    private Float rentPriceFrom;
    private Float rentPriceTo;
}