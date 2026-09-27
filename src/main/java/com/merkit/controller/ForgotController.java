package com.merkit.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.merkit.dto.req.ChangeForgotPassword;
import com.merkit.entity.PasswordResetToken;
import com.merkit.repo.PasswordResetTokenRepository;
import com.merkit.service.AuthService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/forgot")
@RequiredArgsConstructor
public class ForgotController {

	private final AuthService authService;
	private final PasswordResetTokenRepository passwordResetTokenRepository;

	@Value("${front_end}")
	private String fornt_end;

	@GetMapping("/reset")
	public String resetPage(@RequestParam String token, Model model) {

		Optional<PasswordResetToken> opt = passwordResetTokenRepository.findByToken(token);
		if (opt.isPresent()) {
			PasswordResetToken passwordResetToken = opt.get();

			if (passwordResetToken.getUsed()) {
				model.addAttribute("message",
						"This password reset link has already been used. Please request a new reset link to create a new password.");
				return "error-page";

			} else if (passwordResetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
				model.addAttribute("message",
						"This password reset link has expired. Please request a new link to reset your password.");
				return "error-page";

			} else {
				model.addAttribute("token", token);
				return "forgot-page";
			}

		} else {

			model.addAttribute("message",
					"This password reset link is invalid or no longer available. Please request a new reset link to continue.");
			return "error-page";
		}
	}

	@PostMapping("/change")
	public String changePassword(@RequestParam String token, @RequestParam String password,
			@RequestParam String confirmPassword, Model model) {

		if (!password.equals(confirmPassword)) {

			model.addAttribute("message",
					"The passwords you entered do not match. Please make sure both password fields contain the same password.");

			model.addAttribute("token", token);

			return "forgot-page";
		}

		if (password.length() <= 4) {

			model.addAttribute("message",
					"Your password is too short. Please choose a password with more than 4 characters.");

			model.addAttribute("token", token);

			return "forgot-page";
		}

		Optional<PasswordResetToken> opt = passwordResetTokenRepository.findByToken(token);

		if (opt.isPresent()) {

			PasswordResetToken passwordResetToken = opt.get();

			if (passwordResetToken.getUsed()) {

				model.addAttribute("message",
						"This password reset link has already been used. Please request a new reset link to secure your account.");

				return "error-page";

			} else if (passwordResetToken.getExpiresAt().isBefore(LocalDateTime.now())) {

				model.addAttribute("message",
						"This password reset link has expired. Please request a new link to reset your password.");

				return "error-page";

			} else {

				ChangeForgotPassword request = new ChangeForgotPassword();

				request.setPassword(password);
				request.setConfirmPassword(confirmPassword);

				authService.forgotChangePassword(request, token);

				model.addAttribute("front", fornt_end);

				return "success-pages";
			}

		} else {

			model.addAttribute("message",
					"This password reset link is invalid. Please request a new reset link to continue.");

			return "error-page";
		}
	}

	@GetMapping("/secure")
	public String securePage(@RequestParam String token, Model model) {

		authService.stopForgotPassword(token);

		model.addAttribute("front", fornt_end);

		return "secure-page";
	}
}