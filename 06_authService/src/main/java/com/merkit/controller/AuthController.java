package com.merkit.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merkit.dto.req.ChangeForgotPassword;
import com.merkit.dto.req.ChangePasswordRequest;
import com.merkit.dto.req.ForgotPasswordRequest;
import com.merkit.dto.req.LoginRequest;
import com.merkit.dto.req.RefreshTokenRequest;
import com.merkit.dto.req.RegisterRequest;
import com.merkit.dto.res.ApiResponse;
import com.merkit.dto.res.LoginResponse;
import com.merkit.dto.res.TokenResponse;
import com.merkit.service.AuthService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register( @Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED) .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login( @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refreshToken( @Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @PostMapping("/forgot")
    public ResponseEntity<ApiResponse> forgotPassword( @Valid @RequestBody ForgotPasswordRequest request) throws UnsupportedEncodingException, MessagingException {
        return ResponseEntity.ok(authService.forgotPassword(request));
    }
    
//    @PostMapping("/reset-password")
//    public ResponseEntity<ApiResponse> forgotChangePassword(  @Valid @RequestBody ChangeForgotPassword request,@Valid @RequestParam String token) {
//        return ResponseEntity.ok(authService.forgotChangePassword(request, token));
//    }
//    @GetMapping("/stop-reset-password")
//    public ResponseEntity<ApiResponse> stopForgotChangePassword( @Valid @RequestParam String token) {
//    	 return ResponseEntity.ok(authService.stopForgotPassword(token));
//    }

    @PutMapping("/change-password/{userId}")
    public ResponseEntity<ApiResponse> changePassword( @PathVariable Long userId,  @Valid @RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(authService.changePassword(userId, request));
    }
    
    

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout( @RequestParam String refreshToken) {
        return ResponseEntity.ok( authService.logout(refreshToken));
    }

	

}