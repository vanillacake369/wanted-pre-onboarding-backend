package com.wanted.onboarding.dto;

import com.wanted.onboarding.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
public class SignRequestDto {
    @NotBlank(message = "Enter the email")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @NotBlank(message = "Enter the password")
    @Size(min = 8,message = "password should have at least 8 characters")
    private String password;
}
