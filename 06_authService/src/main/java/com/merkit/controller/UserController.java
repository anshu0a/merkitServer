package com.merkit.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merkit.dto.res.UserResponse;
import com.merkit.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
 

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(
            @PathVariable String username) {

        return ResponseEntity.ok(
                userService.getUserByUsername(username));
    }
    
    @GetMapping("/existusername/{username}")
    public ResponseEntity<Boolean> getUserExistUsername(
            @PathVariable String username) {

        return ResponseEntity.ok(
                userService.getUserNameExist(username));
    }
    
    @GetMapping("/existemail/{mail}")
    public ResponseEntity<Boolean> getUserExistEmail(
            @PathVariable String mail) {

        return ResponseEntity.ok(
                userService.getEmailExist(mail));
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers());
    }

	

}