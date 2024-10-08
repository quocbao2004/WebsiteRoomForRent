package com.javaweb.WebsiteRoomForRent.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO {

    @Min(value = 1, message = "Product's ID must be > 0")
    private Long buildingId;

    @Size(min = 5, max = 200, message = "Image's name")
    private String imageUrl;
}
