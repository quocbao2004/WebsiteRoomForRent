package com.javaweb.WebsiteRoomForRent.services.impl;


import com.javaweb.WebsiteRoomForRent.dtos.MailMessageDTO;
import com.javaweb.WebsiteRoomForRent.entities.ForgotPassEntity;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import com.javaweb.WebsiteRoomForRent.repository.ForgotPassRepo;
import com.javaweb.WebsiteRoomForRent.repository.UserRepository;
import com.javaweb.WebsiteRoomForRent.services.EmailService;
import com.javaweb.WebsiteRoomForRent.services.ForgotPassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ForgotPassServiceImpl implements ForgotPassService {

    private final UserRepository userRepo;
    private final ForgotPassRepo forgotPassRepo;
    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public String sendOTP(String phone) {
        UserEntity user = userRepo.findByPhone(phone).get();
        if (user == null) throw new UsernameNotFoundException("User not found");
        String otp = genOTP();
        Date date = new Date(System.currentTimeMillis() + 60 * 1000L);
        ForgotPassEntity forgotPass = new ForgotPassEntity();
        forgotPass.setOtp(otp);
        forgotPass.setUser(user);
        forgotPass.setExpiration(date);
//        forgotPass.builder()
//                .otp(otp)
//                .user(user)
//                .expiration(date)
//                .build();
        forgotPassRepo.save(forgotPass);
        MailMessageDTO mailMessageDTO = new MailMessageDTO();
//        mailMessageDTO.builder()
//                .from(from)
//                .to(email)
//                .body(otp)
//                .subject("Forgot Password")
//                .build();
        mailMessageDTO.setFrom(from);
        mailMessageDTO.setTo(user.getEmail());
        mailMessageDTO.setBody(otp);
        mailMessageDTO.setSubject("Forgot Pass OTP");
        emailService.sendEmail(mailMessageDTO);
        return "Sent Successfully";
    }

    private String genOTP() {
        Random rand = new Random();
        return String.valueOf(rand.nextInt(100000, 999999));
    }

    @Override
    public String verifyOTP(String phone, String otp) {
        ForgotPassEntity forgotPassEntity = forgotPassRepo.findByOtpAndUserUsername(otp, phone);
        if(forgotPassEntity == null) return "Invalid OTP";
        if(forgotPassEntity.getExpiration().getTime() < new Date().getTime()) {
            forgotPassRepo.delete(forgotPassEntity);
            return "Expired";
        }
        forgotPassRepo.delete(forgotPassEntity);
        return "Correct OTP";
    }
}
