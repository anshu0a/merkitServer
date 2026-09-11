package com.merkit.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.merkit.dto.res.OtpResponse;
import com.merkit.entity.OTP;
import com.merkit.repo.OTPRepository;
import com.merkit.service.OTPService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService {

	@Value("${otp.exp}")
    private long EXPIRY_SECONDS;

    private final OTPRepository otpRepository;
    private final EmailService emailService;

    @Override
    public Boolean addOtp(String email) throws MessagingException, UnsupportedEncodingException {

        Optional<OTP> existing =
                otpRepository.findFirstByEmailOrderByIdDesc(email);

        if (existing.isPresent() && existing.get().valid()) {
            emailService.sendOtp(email, existing.get().getOtp());
            return true;
        }

        Integer code = generateOtp();

        OTP otp;

        if (existing.isPresent()) {
            otp = existing.get();
            otp.setOtp(code);
            otp.setExp(java.time.LocalDateTime.now()
                    .plusSeconds(EXPIRY_SECONDS));
        } else {
            otp = new OTP(code, email, EXPIRY_SECONDS);
        }

        OTP saved = otpRepository.save(otp);

        emailService.sendOtp(email, saved.getOtp());

        return true;
    }

    @Override
    public Boolean addOtp(Long mobile) {

        Optional<OTP> existing =
                otpRepository.findFirstByMobileOrderByIdDesc(mobile);

        if (existing.isPresent() && existing.get().valid()) {
            return true;
        }

        Integer code = generateOtp();

        OTP otp;

        if (existing.isPresent()) {
            otp = existing.get();
            otp.setOtp(code);
            otp.setExp(java.time.LocalDateTime.now()
                    .plusSeconds(EXPIRY_SECONDS));
        } else {
            otp = new OTP(code, mobile, EXPIRY_SECONDS);
        }

        otpRepository.save(otp);

        return true;
    }

    @Override
    public OtpResponse validOtp(String email, Integer otp) {

        return verify(
                otpRepository.findFirstByEmailOrderByIdDesc(email),
                otp
        );
    }

    @Override
    public OtpResponse validOtp(Long mobile, Integer otp) {

        return verify(
                otpRepository.findFirstByMobileOrderByIdDesc(mobile),
                otp
        );
    }

    private OtpResponse verify(
            Optional<OTP> existing,
            Integer otp) {

        if (existing.isEmpty()) {
            return new OtpResponse(false, "OTP not found");
        }

        OTP saved = existing.get();

        if (!saved.valid()) {
            return new OtpResponse(false, "OTP expired");
        }

        if (!saved.getOtp().equals(otp)) {
            return new OtpResponse(false, "Invalid OTP");
        }

        return new OtpResponse(
                true,
                "OTP verified successfully"
        );
    }

    private Integer generateOtp() {
        return 10000 + new Random().nextInt(90000);
    }
}