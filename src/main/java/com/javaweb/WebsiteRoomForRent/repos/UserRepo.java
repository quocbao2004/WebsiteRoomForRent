package com.javaweb.WebsiteRoomForRent.repos;

import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
}
