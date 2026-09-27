package com.merkit.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.merkit.entity.CloudinaryFile;
import com.merkit.service.CloudinaryFileService;

@Service
public class CloudinaryFileServiceImpl implements CloudinaryFileService {

    private final Cloudinary cloudinary;

    public CloudinaryFileServiceImpl(
            @Value("${cloudinary.cloud-name}") String cloudName,
            @Value("${cloudinary.api-key}") String apiKey,
            @Value("${cloudinary.api-secret}") String apiSecret) {

        this.cloudinary = new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", cloudName,
                        "api_key", apiKey,
                        "api_secret", apiSecret
                )
        );
    }

    @Override
    public CloudinaryFile upload(
            MultipartFile file,
            String category
    ) throws IOException {

        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto",
                        "folder", "merkit/capsules"
                )
        );

        CloudinaryFile cloudinaryFile = new CloudinaryFile();

        cloudinaryFile.setPublicId(
                result.get("public_id").toString()
        );

        cloudinaryFile.setSecureUrl(
                result.get("secure_url").toString()
        );

        cloudinaryFile.setResourceType(
                result.get("resource_type").toString()
        );

        if (result.get("format") != null) {
            cloudinaryFile.setFormat(
                    result.get("format").toString()
            );
        }

        cloudinaryFile.setOriginalFilename(
                file.getOriginalFilename()
        );

        cloudinaryFile.setBytes(
                file.getSize()
        );

        cloudinaryFile.setCategory(category);

        if (result.get("asset_folder") != null) {
            cloudinaryFile.setFolder(
                    result.get("asset_folder").toString()
            );
        }

        if (result.get("width") != null) {
            cloudinaryFile.setWidth(
                    ((Number) result.get("width")).intValue()
            );
        }

        if (result.get("height") != null) {
            cloudinaryFile.setHeight(
                    ((Number) result.get("height")).intValue()
            );
        }

        if (result.get("duration") != null) {
            cloudinaryFile.setDuration(
                    ((Number) result.get("duration")).doubleValue()
            );
        }

        return cloudinaryFile;
    }

    @Override
    public void delete(
            CloudinaryFile file
    ) throws IOException {

        if (file == null || file.getPublicId() == null) {
            return;
        }

        cloudinary.uploader().destroy(
                file.getPublicId(),
                ObjectUtils.asMap(
                        "resource_type",
                        file.getResourceType(),
                        "type",
                        "upload"
                )
        );
    }
}