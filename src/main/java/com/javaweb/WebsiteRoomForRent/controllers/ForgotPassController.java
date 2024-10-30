package com.javaweb.WebsiteRoomForRent.controllers;

import com.javaweb.WebsiteRoomForRent.services.ForgotPassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}/password")
@Transactional
@RequiredArgsConstructor
public class ForgotPassController {
    private final ForgotPassService forgotPassService;

    @GetMapping("/send-otp")
    public ResponseEntity verifyEmail(@RequestParam String phone) {
        try {
            return ResponseEntity.ok(forgotPassService.sendOTP(phone));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/check-otp")
    public ResponseEntity verifyOTP(@RequestParam String otp, @RequestParam String phone) {
        try {
            return ResponseEntity.ok(forgotPassService.verifyOTP(phone, otp));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
