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

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String fullName;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String phone;

    @NotNull(message = "Building's name can't be null")
    @NotBlank(message = "Building's name can't be blank")
    private String email;

    private String demand;
}
