package com.javaweb.WebsiteRoomForRent.services;

import com.javaweb.WebsiteRoomForRent.dtos.PasswordDTO;
import com.javaweb.WebsiteRoomForRent.dtos.UserDTO;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;

public interface UserService {
    UserEntity createUser(UserDTO userDTO) throws Exception;
    String login(String phoneNumber, String password) throws Exception;
    void revokeToken(String token);
    String changePassword(PasswordDTO password, UserEntity user) throws Exception;
    String editProfile(UserDTO userDTO) throws Exception;
    UserDTO getUser(Long id);
}
