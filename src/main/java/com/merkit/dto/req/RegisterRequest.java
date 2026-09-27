package com.merkit.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 100)
    private String username;

    @NotBlank(message = "First name is required")
    @Size(max = 100)
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
    private String mobile;

    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 20)
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

}
