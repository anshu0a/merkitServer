package com.merkit.dto.res;

import com.merkit.enums.ArticleResponseStatus;

import lombok.Builder;

@Builder
public record ArticleCreateResponse(String message, ArticleResponseStatus status) {

}
