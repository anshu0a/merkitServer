package com.merkit.dto.res;

import com.merkit.enums.UserStatus;

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
public class UserResponse {

    private Long id;

    private String username;

    private String name;

    private String email;

    private String mobile;

    private UserStatus status;

    private Boolean enabled;

    private Boolean emailVerified;

    private Boolean mobileVerified;

}
