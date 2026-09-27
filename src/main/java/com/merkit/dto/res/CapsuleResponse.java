package com.merkit.dto.res;

import java.time.LocalDateTime;

import com.merkit.entity.CloudinaryFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapsuleResponse {

    private Long id;

    private Long userId;
    private String username;
    private String profilepic;
    private String name;
    
    private String title;
    private String description;

    private Double latitude;
    private Double longitude;
    private String location;

    private Integer views;
    private Integer likes;

    private CloudinaryFile thumbnail;
    private String attachments;

    private LocalDateTime openDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
