package com.javaweb.WebsiteRoomForRent.services;

public interface OtpService {
    void saveOtp(String email, String otp);
    boolean validateOtp(String email, String otp);
}
