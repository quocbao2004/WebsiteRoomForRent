package com.javaweb.WebsiteRoomForRent.services;

public interface EmailService {
    public void sendSimpleEmail(String toEmail, String subject, String body);
}
