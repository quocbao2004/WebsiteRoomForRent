package com.javaweb.WebsiteRoomForRent.services.impl;

import com.javaweb.WebsiteRoomForRent.entities.PasswordResetToken;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import com.javaweb.WebsiteRoomForRent.repository.PasswordResetTokenRepository;
import com.javaweb.WebsiteRoomForRent.repository.UserRepository;
import com.javaweb.WebsiteRoomForRent.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OtpServiceImpl implements OtpService {
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private UserRepository userRepository;

    public void saveOtp(String email, String otp) {
        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);
        token.setOtpCode(otp);
        token.setExpiration_time(LocalDateTime.now().plusMinutes(1)); // Hết hạn sau 1 phút
        passwordResetTokenRepository.save(token);
    }

    public boolean validateOtp(String username, String otp) {
        UserEntity user = userRepository.findByUsername(username);
        String email = user.getEmail();
        PasswordResetToken token = passwordResetTokenRepository.findByEmailAndOtpCode(email, otp);
        if (token != null && token.getExpiration_time().isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }
}
