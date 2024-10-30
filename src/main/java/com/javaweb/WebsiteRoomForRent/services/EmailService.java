package com.javaweb.WebsiteRoomForRent.services;

import com.javaweb.WebsiteRoomForRent.dtos.MailMessageDTO;

public interface EmailService {
    public String sendEmail(MailMessageDTO mailMessageDTO);
}