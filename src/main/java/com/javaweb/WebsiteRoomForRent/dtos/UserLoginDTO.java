package com.javaweb.WebsiteRoomForRent.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data //toString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @JsonProperty("phone")
    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "Password can not be blank")
    private String password;
}
