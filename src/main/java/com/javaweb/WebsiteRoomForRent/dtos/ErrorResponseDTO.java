package com.javaweb.WebsiteRoomForRent.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {
    private String error;
    private List<String> details;
}