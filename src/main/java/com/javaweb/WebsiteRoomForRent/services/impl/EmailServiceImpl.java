package com.javaweb.WebsiteRoomForRent.services.impl;

import com.javaweb.WebsiteRoomForRent.dtos.MailMessageDTO;
import com.javaweb.WebsiteRoomForRent.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public String sendEmail(MailMessageDTO mailMessageDTO) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(mailMessageDTO.getTo());
            simpleMailMessage.setSubject(mailMessageDTO.getSubject());
            simpleMailMessage.setFrom(mailMessageDTO.getFrom());
            simpleMailMessage.setText(mailMessageDTO.getBody());
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Sent OTP. Please check your email";
    }
}