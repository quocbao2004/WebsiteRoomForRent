package com.javaweb.WebsiteRoomForRent.services;

import com.javaweb.WebsiteRoomForRent.dtos.UserDTO;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;

public interface UserService {
    UserEntity createUser(UserDTO userDTO) throws Exception;
    String login(String phoneNumber, String password) throws Exception;
}
