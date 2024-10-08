package com.javaweb.WebsiteRoomForRent.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotNull(message = "Building's id can't be null")
    private Integer id;
    private String fullName;
    private String phone;
    private String email;
    private String demand;
}
