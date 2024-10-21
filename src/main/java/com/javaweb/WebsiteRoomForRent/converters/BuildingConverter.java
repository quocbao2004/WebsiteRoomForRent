package com.javaweb.WebsiteRoomForRent.converters;

import com.javaweb.WebsiteRoomForRent.dtos.BuildingDTO;
import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.requests.BuildingSearchRequests;
import com.javaweb.WebsiteRoomForRent.responses.BuildingSearchResponse;
import com.javaweb.WebsiteRoomForRent.utils.BuildingSearchRequestUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class BuildingConverter {

    private final ModelMapper modelMapper;
    private final BuildingSearchRequestUtil buildingSearchRequestUtil;

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity buildingEntity) {
        BuildingSearchResponse res = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        res.setImages(buildingEntity.getImages().stream().map(it -> it.getImageUrl()).toList());
        return res;
    }

    public BuildingSearchRequests toBuildingSearchRequests(Map<String, Object> mp) {
        BuildingSearchRequests buildingSearchRequests = new BuildingSearchRequests();
        buildingSearchRequests.setId(buildingSearchRequestUtil.getObject(mp.get("id"), Long.class));
        buildingSearchRequests.setName(buildingSearchRequestUtil.getObject(mp.get("name"), String.class));
        buildingSearchRequests.setWard(buildingSearchRequestUtil.getObject(mp.get("ward"), String.class));
        buildingSearchRequests.setType(buildingSearchRequestUtil.getObject(mp.get("type"), String.class));
        buildingSearchRequests.setDistrict(buildingSearchRequestUtil.getObject(mp.get("district"), String.class));
        buildingSearchRequests.setStreet(buildingSearchRequestUtil.getObject(mp.get("street"), String.class));
        buildingSearchRequests.setFloorAreaFrom(buildingSearchRequestUtil.getObject(mp.get("floorAreaFrom"), Long.class));
        buildingSearchRequests.setFloorAreaTo(buildingSearchRequestUtil.getObject(mp.get("floorAreaTo"), Long.class));
        buildingSearchRequests.setRentPriceFrom(buildingSearchRequestUtil.getObject(mp.get("rentPriceFrom"), Long.class));
        buildingSearchRequests.setRentPriceTo(buildingSearchRequestUtil.getObject(mp.get("rentPriceTo"), Long.class));
        return buildingSearchRequests;
    }

    public BuildingEntity toBuildingEntity(BuildingDTO dto) {
        return modelMapper.map(dto, BuildingEntity.class);
    }
}