package com.merkit.service;

import com.merkit.entity.CloudinaryFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryFileService {

    CloudinaryFile upload(MultipartFile file, String category) throws IOException;

    void delete(CloudinaryFile file) throws IOException;
}