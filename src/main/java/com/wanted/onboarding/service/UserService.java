package com.wanted.onboarding.service;


import com.wanted.onboarding.dto.SignRequestDto;
import com.wanted.onboarding.dto.SignResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    /* Get Users by email addr */
    SignResponseDto findUserByEmail(String email) throws Exception;
    /* Authentication */
    UserDetails loadUserByEmail(String email) throws Exception;
    /* Sign Up */
    SignResponseDto signUp(SignRequestDto user) throws Exception;
    /* Sign In */
    Boolean signIn(SignRequestDto user) throws Exception;



}
