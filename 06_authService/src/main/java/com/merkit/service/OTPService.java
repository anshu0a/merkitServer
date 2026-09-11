package com.merkit.service;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.merkit.dto.res.OtpResponse;

import jakarta.mail.MessagingException;

@Component
public interface OTPService {
	
	 public Boolean addOtp(String email) throws MessagingException, UnsupportedEncodingException;
	    public Boolean addOtp(Long mobile);
	    public OtpResponse validOtp(String email, Integer otp);
	    public OtpResponse validOtp(Long mobile, Integer otp);

	
}
