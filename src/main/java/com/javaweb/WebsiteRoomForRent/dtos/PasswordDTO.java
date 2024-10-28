package com.javaweb.WebsiteRoomForRent.dtos;

import lombok.Data;

@Data
public class PasswordDTO {
    private Long id;
    private String oldPassword;
    private String newPassword;
    private String retypePassword;
    private String username;
}
