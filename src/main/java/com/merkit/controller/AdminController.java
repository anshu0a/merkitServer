package com.merkit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merkit.dto.res.ApiResponse;
import com.merkit.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PutMapping("/enable/{id}")
    public ResponseEntity<ApiResponse> enableUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.enableUser(id));
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<ApiResponse> disableUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.disableUser(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.deleteUser(id));
    }

}