package com.merkit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merkit.entity.CloudinaryFile;

public interface CloudinaryRepository extends JpaRepository<CloudinaryFile, Long> {
}
