package com.merkit.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merkit.dto.res.OtpResponse;
import com.merkit.service.impl.OTPServiceImpl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OTPServiceImpl otpService;

    @PostMapping("/mobile/{mobile}")
    public ResponseEntity<Boolean> getOtpByMobile(
            @PathVariable Long mobile) {

        return ResponseEntity.ok(
                otpService.addOtp(mobile)
        );
    }

    @PostMapping("/email/{email}")
    public ResponseEntity<Boolean> getOtpByEmail(
            @PathVariable String email) throws MessagingException, UnsupportedEncodingException {

        return ResponseEntity.ok(
                otpService.addOtp(email)
        );
    }

    @PostMapping("/mobile/{mobile}/{otp}")
    public ResponseEntity<OtpResponse> checkOtpByMobile(
            @PathVariable Long mobile,
            @PathVariable Integer otp) {

        return ResponseEntity.ok(
                otpService.validOtp(mobile, otp)
        );
    }

    @PostMapping("/email/{email}/{otp}")
    public ResponseEntity<OtpResponse> checkOtpByEmail(
            @PathVariable String email,
            @PathVariable Integer otp) {

        return ResponseEntity.ok(
                otpService.validOtp(email, otp)
        );
    }
}