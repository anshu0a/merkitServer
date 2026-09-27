package com.merkit.dto.res;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;

@Builder
public record ArticleResponse(
		Long id, 
		String title, 
		String article,
		
		String userimg,
		String username,
		String name,
		
		LocalDateTime date,
		List<String[]> imgs,
		String hashtag,
		
		Integer likes,
		Integer views,
		Integer comments
		) {

}
