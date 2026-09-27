package com.merkit.service;


import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.merkit.dto.res.ApiResponse;
import com.merkit.dto.res.CapsuleResponse;
import com.merkit.entity.TimeCapsule;

public interface TimeCapsuleService {

    ApiResponse create(
            TimeCapsule capsule,
            MultipartFile thumbnail,
            List<MultipartFile> images,
            List<MultipartFile> videos,
            List<MultipartFile> pdfs,
            List<MultipartFile> files
    ) throws IOException;

    CapsuleResponse getById(Long id);

    Page<CapsuleResponse> getAll(Pageable pageable);

    void delete(Long id) throws IOException;
}