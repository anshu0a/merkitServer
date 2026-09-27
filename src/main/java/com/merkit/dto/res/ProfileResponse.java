package com.merkit.dto.res;

import java.time.LocalDateTime;
import java.util.List;

import com.merkit.entity.LoginHistory;

import lombok.Builder;

@Builder
public record ProfileResponse(
	    Long id,
	    String username,
	    String profilepic,
        String name,
        
        String email,
        String bio,
        
        List<String[]> SocalLinks,
        
	    LocalDateTime lastLoginAt,
	    LocalDateTime createdAt,
	    LocalDateTime updatedAt,
	    
	    List<LoginHistory> loginHistory
		) { 

}
