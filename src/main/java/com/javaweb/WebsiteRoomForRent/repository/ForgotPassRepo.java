package com.javaweb.WebsiteRoomForRent.repository;

import com.javaweb.WebsiteRoomForRent.entities.ForgotPassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPassRepo extends JpaRepository<ForgotPassEntity, Long> {
    ForgotPassEntity findByOtpAndUserUsername (String otp, String username);
}