package com.merkit.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.merkit.dto.res.ApiResponse;
import com.merkit.dto.res.CapsuleResponse;
import com.merkit.entity.TimeCapsule;
import com.merkit.entity.User;
import com.merkit.exception.UserNotFoundException;
import com.merkit.repo.UserRepository;
import com.merkit.service.TimeCapsuleService;

@RestController
@RequestMapping("/capsule")
public class CapsuleController {

	private final TimeCapsuleService timeCapsuleService;

	private final UserRepository userRepository;

	public CapsuleController(TimeCapsuleService timeCapsuleService, UserRepository userRepository) {

		this.timeCapsuleService = timeCapsuleService;
		this.userRepository = userRepository;
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<ApiResponse> create(@RequestPart("capsule") TimeCapsule capsule,
			@RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail,
			@RequestPart(value = "images", required = false) List<MultipartFile> images,
			@RequestPart(value = "videos", required = false) List<MultipartFile> videos,
			@RequestPart(value = "pdfs", required = false) List<MultipartFile> pdfs,
			@RequestPart(value = "files", required = false) List<MultipartFile> files) throws IOException {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
		capsule.setUser(user);

		ApiResponse saved = timeCapsuleService.create(capsule, thumbnail, images, videos, pdfs, files);

		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CapsuleResponse> getById(@PathVariable Long id) {

		return ResponseEntity.ok(timeCapsuleService.getById(id));
	}

	@GetMapping
	public ResponseEntity<Page<CapsuleResponse>> getAll(
	        @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

	    return ResponseEntity.ok(timeCapsuleService.getAll(pageable));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws IOException {

		timeCapsuleService.delete(id);

		return ResponseEntity.ok("Time capsule deleted successfully");
	}
}