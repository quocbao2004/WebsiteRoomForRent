package com.javaweb.WebsiteRoomForRent.requests;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuildingSearchRequests {

    private String name;
    private String ward;
    private String type;
    private String district;
    private String street;
    private Long floorArea;
    private Long rentPrice;
}
