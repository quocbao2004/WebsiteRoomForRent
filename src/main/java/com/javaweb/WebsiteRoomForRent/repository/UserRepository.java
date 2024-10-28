package com.javaweb.WebsiteRoomForRent.repository;

import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPhone(String phoneNumber);
    UserEntity findByUsername(String username);
}
