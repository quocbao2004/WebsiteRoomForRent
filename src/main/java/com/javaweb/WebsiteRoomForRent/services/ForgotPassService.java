package com.javaweb.WebsiteRoomForRent.services;

public interface ForgotPassService {
    public String sendOTP(String phone);
    public String verifyOTP(String phone, String otp);
}