package com.javaweb.WebsiteRoomForRent.controllers;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import com.javaweb.WebsiteRoomForRent.repository.UserRepository;
import com.javaweb.WebsiteRoomForRent.services.OtpService;
import com.javaweb.WebsiteRoomForRent.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/password-reset")
@CrossOrigin(origins = "*")
public class PasswordResetController {
    @Autowired
    private PasswordResetService passwordResetService;
    @Autowired
    private OtpService otpService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String username) {
        UserEntity user = userRepository.findByUsername(username);
        if(user != null) {
            passwordResetService.sendOtp(user.getEmail());
            return ResponseEntity.ok("OTP sent to your email.");
        }
        return ResponseEntity.badRequest().body("OTP could not be sent to your email.");
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOtp(@RequestParam String username, @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(username, otp);
        if (isValid) {
            return ResponseEntity.ok("OTP verified.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP.");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String username, @RequestParam String newPassword) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok("Password reset successful. Please log in with your new password.");
        }
        return ResponseEntity.badRequest().body("User not found.");
    }
}
