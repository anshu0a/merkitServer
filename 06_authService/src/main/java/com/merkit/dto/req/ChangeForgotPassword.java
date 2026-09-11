package com.merkit.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChangeForgotPassword {
	
//	@NotBlank(message = "Email is required")
//    private String email;

    @NotBlank(message = "New password is required")
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

}
