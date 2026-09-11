package com.merkit.dto.req;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ArticleRequest {

    @NotBlank(message = "Article title is required")
    private String title;

    @NotBlank(message = "Article is required")
    private String article;

    @NotBlank(message = "Article hashtag is required")
    private String tag;

    private List<MultipartFile> images;

    private boolean publish;

    private LocalDateTime publishTime;
}