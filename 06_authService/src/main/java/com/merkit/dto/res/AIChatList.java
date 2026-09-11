package com.merkit.dto.res;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record AIChatList(String name, Long chatId, Integer question , LocalDateTime dateStamp ,Boolean saved) {

}
