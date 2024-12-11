package com.javaweb.WebsiteRoomForRent.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;


@Data //toString
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String address;

    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("phone")
    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "Password can not be blank")
    private String password;

    @JsonProperty("retype_password")
    private String retypePassword;

    @JsonProperty("username")
    private String username;

    @NotNull(message = "Role id is required")
    @JsonProperty("role_id")
    private Long roleId;
}