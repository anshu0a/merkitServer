package com.merkit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cloudinary_files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CloudinaryFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_capsule_id")
    private TimeCapsule timeCapsule;

    @Column(nullable = false, length = 500)
    private String publicId;

    @Column(nullable = false, length = 1000)
    private String secureUrl;

    private String resourceType;

    private String format;

    private String mimeType;

    private String originalFilename;

    private Long bytes;

    private Integer width;

    private Integer height;

    private Double duration;

    private String folder;

    private String category;
}