package com.merkit.service.impl;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.merkit.dto.res.ApiResponse;
import com.merkit.dto.res.CapsuleResponse;
import com.merkit.entity.CloudinaryFile;
import com.merkit.entity.TimeCapsule;
import com.merkit.entity.User;
import com.merkit.repo.TimeCapsuleRepository;
import com.merkit.service.CloudinaryFileService;
import com.merkit.service.TimeCapsuleService;

@Service
public class TimeCapsuleServiceImpl implements TimeCapsuleService {

	private final TimeCapsuleRepository timeCapsuleRepository;

	private final CloudinaryFileService cloudinaryFileService;

	public TimeCapsuleServiceImpl(TimeCapsuleRepository timeCapsuleRepository,
			CloudinaryFileService cloudinaryFileService) {

		this.timeCapsuleRepository = timeCapsuleRepository;
		this.cloudinaryFileService = cloudinaryFileService;
	}

	@Override
	@Transactional
	public ApiResponse create(TimeCapsule capsule, MultipartFile thumbnail, List<MultipartFile> images,
			List<MultipartFile> videos, List<MultipartFile> pdfs, List<MultipartFile> files) throws IOException {

		List<CloudinaryFile> uploadedFiles = new ArrayList<>();

		try {

			if (thumbnail != null && !thumbnail.isEmpty()) {

				CloudinaryFile cloudinaryFile = cloudinaryFileService.upload(thumbnail, "THUMBNAIL");
				uploadedFiles.add(cloudinaryFile);
				cloudinaryFile.setTimeCapsule(capsule);
				capsule.setThumbnail(cloudinaryFile);
			}

			uploadFiles(capsule, images, "IMAGE", uploadedFiles);
			uploadFiles(capsule, videos, "VIDEO", uploadedFiles);
			uploadFiles(capsule, pdfs, "PDF", uploadedFiles);
			uploadFiles(capsule, files, "FILE", uploadedFiles);

			if (capsule.getViews() == null) capsule.setViews(new ArrayList<>());
			if (capsule.getLikes() == null) capsule.setLikes(new ArrayList<>());

			LocalDateTime now = LocalDateTime.now();

			capsule.setCreatedAt(now);
			capsule.setUpdatedAt(now);
			timeCapsuleRepository.save(capsule);
			
			return ApiResponse.builder().statusCode(200).success(true).message("capsule planted . . .").build();

		} catch (Exception e) {

			for (CloudinaryFile file : uploadedFiles) {

				try {
					cloudinaryFileService.delete(file);
				} catch (Exception ignored) {
				}
			}

			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public CapsuleResponse getById(Long id) {

		TimeCapsule capsule = timeCapsuleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Time capsule not found"));

		return toResponse(capsule);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CapsuleResponse> getAll(Pageable pageable) {
	    return timeCapsuleRepository.findAll(pageable)
	            .map(this::toResponse);
	}

	@Override
	@Transactional
	public void delete(Long id) throws IOException {

		TimeCapsule capsule = timeCapsuleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Time capsule not found"));

		if (capsule.getThumbnail() != null) {

			cloudinaryFileService.delete(capsule.getThumbnail());
		}

		if (capsule.getAttachments() != null) {

			for (CloudinaryFile file : capsule.getAttachments()) {

				cloudinaryFileService.delete(file);
			}
		}

		timeCapsuleRepository.delete(capsule);
	}
//	------------------------------------------------------------------------------------------
	
	private void uploadFiles(TimeCapsule capsule, List<MultipartFile> files, String category,
			List<CloudinaryFile> uploadedFiles) throws IOException {
		
		if (files == null || files.isEmpty()) {
			return;
		}
		
		for (MultipartFile file : files) {
			
			if (file == null || file.isEmpty()) {
				continue;
			}
			
			CloudinaryFile cloudinaryFile = cloudinaryFileService.upload(file, category);
			
			uploadedFiles.add(cloudinaryFile);
			
			cloudinaryFile.setTimeCapsule(capsule);
			
			if (capsule.getAttachments() == null) {
				capsule.setAttachments(new ArrayList<>());
			}
			
			capsule.getAttachments().add(cloudinaryFile);
		}
	}
	
	private CapsuleResponse toResponse(TimeCapsule cap) {
		
		User user = cap.getUser();
		
		String result = cap.getAttachments().stream()
		        .collect(Collectors.groupingBy(
		                CloudinaryFile::getResourceType,
		                Collectors.counting()
		        ))
		        .entrySet()
		        .stream()
		        .map(e -> e.getValue() + "." + e.getKey())
		        .collect(Collectors.joining(","));

		return CapsuleResponse.builder()
				.id(cap.getId())
				.userId(user.getId()).name(user.getName()).username(user.getUsername()).profilepic(user.getProfilepic())
				.title(cap.getTitle()).description(cap.getDescription())
				.longitude(cap.getLongitude()).latitude(cap.getLatitude())
				.openDate(cap.getOpenDate()).createdAt(cap.getCreatedAt()).updatedAt(cap.getUpdatedAt())
				.views(cap.getViews().size()).likes(cap.getLikes().size())
				.location(cap.getLocation())
				.thumbnail(cap.getThumbnail()).attachments(result)
				.build();
	}
	
}