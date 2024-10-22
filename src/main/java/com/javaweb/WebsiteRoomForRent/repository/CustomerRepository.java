package com.javaweb.WebsiteRoomForRent.repository;

import com.javaweb.WebsiteRoomForRent.entities.CustomerEntity;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}