package com.javaweb.WebsiteRoomForRent.requests;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuildingSearchRequests {

    private Long id;
    private String name;
    private String ward;
    private String type;
    private String district;
    private String street;
    private Long floorArea;
    private Long rentPrice;
}
