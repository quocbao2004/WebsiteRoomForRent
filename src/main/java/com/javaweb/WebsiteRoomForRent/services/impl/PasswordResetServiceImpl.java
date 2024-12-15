package com.javaweb.WebsiteRoomForRent.services.impl;

import com.javaweb.WebsiteRoomForRent.configurations.OtpGenerator;
import com.javaweb.WebsiteRoomForRent.services.EmailService;
import com.javaweb.WebsiteRoomForRent.services.OtpService;
import com.javaweb.WebsiteRoomForRent.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    public void sendOtp(String email) {
        String otp = OtpGenerator.generateOtp(); // Sinh mã OTP
        otpService.saveOtp(email, otp);         // Lưu OTP vào DB
        String subject = "Password Reset Request";
        String body = "Your OTP code is: " + otp + ". It will expire in 1 minute.";
        emailService.sendSimpleEmail(email, subject, body); // Gửi OTP qua email
    }
}
