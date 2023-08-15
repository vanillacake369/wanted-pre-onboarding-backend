package com.wanted.onboarding.dto;

import com.wanted.onboarding.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponseDto {
    private Long id;
    private String email;
    private String password;

    private String token;

    public SignResponseDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

}
