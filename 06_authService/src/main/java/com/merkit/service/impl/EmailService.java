package com.merkit.service.impl;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.merkit.entity.PasswordResetToken;
import com.merkit.mail.Mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String mailUsername;

	@Value("${otp.exp}")
	private long EXPIRY_SECONDS;
	@Value("${back_end}")
	private String back_end;

	public void sendOtp(String email, Integer otp) throws MessagingException, UnsupportedEncodingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		helper.setTo(email);
		helper.setFrom(mailUsername, "Merkit");
		helper.setReplyTo(email);
		helper.setSubject("Merkit - Your Verification Code");

		String expiryTime = LocalDateTime.now().plusSeconds(EXPIRY_SECONDS).format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a"));

		String html = Mail.OTP.formatted(email, otp, expiryTime);
		
		helper.setText(html, true);

		mailSender.send(message);
	}
	
	
	public void sendForgot(PasswordResetToken resetLink) throws MessagingException, UnsupportedEncodingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		helper.setTo(resetLink.getUser().getEmail());
		helper.setFrom(mailUsername, "Merkit");
		helper.setReplyTo(resetLink.getUser().getEmail());
		helper.setSubject("Merkit - Your Forgot password link");

		String expiryTime = resetLink.getExpiresAt().format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a"));
		String url = back_end+"/forgot/reset?token="+resetLink.getToken(); 
		
		
		String html = Mail.FORGOT.formatted(
			    resetLink.getUser().getUsername(),
			    resetLink.getUser().getEmail(),
			    expiryTime,
			    url,
			    url
			);
		
		helper.setText(html, true);

		mailSender.send(message);
	}
}