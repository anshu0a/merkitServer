package com.merkit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merkit.dto.req.SocalLinkRequest;
import com.merkit.dto.res.ApiResponse;
import com.merkit.dto.res.ProfileResponse;
import com.merkit.dto.res.UserResponse;
import com.merkit.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}

	@GetMapping("/profile/{username}")
	public ResponseEntity<ProfileResponse> getUserByUsernameForProfile(@PathVariable String username) {
		return ResponseEntity.ok(userService.getUserByUsernameForProfile(username));
	}

	@GetMapping("/socallink")
	public ResponseEntity<ApiResponse> addSocialLink(@RequestParam String name,@RequestParam String link, Authentication auth) {

		String username = auth.getName();
		
		return ResponseEntity.ok(userService.addSocalLink(
				SocalLinkRequest.builder().name(name).link(link).build(),username));
	}
	@DeleteMapping("/socallink")
	public ResponseEntity<ApiResponse> deleteSocialLink(@RequestParam String name, Authentication auth) {
		String username = auth.getName();
		return ResponseEntity.ok(userService.deleteSocalLink(name, username));
	}

	@GetMapping("/existusername/{username}")
	public ResponseEntity<Boolean> getUserExistUsername(@PathVariable String username) {
		return ResponseEntity.ok(userService.getUserNameExist(username));
	}

	@GetMapping("/existemail/{mail}")
	public ResponseEntity<Boolean> getUserExistEmail(@PathVariable String mail) {
		return ResponseEntity.ok(userService.getEmailExist(mail));
	}

	@GetMapping("/search")
	public ResponseEntity<List<String[]>> searchUsername(@RequestParam String regx) {
		return new ResponseEntity<List<String[]>>(userService.searchUser(regx), HttpStatus.OK);
	}

//-----------------------------------------------------------
	@GetMapping("")
	public ResponseEntity<List<UserResponse>> getAllUsers() {

		return ResponseEntity.ok(userService.getAllUsers());
	}

}