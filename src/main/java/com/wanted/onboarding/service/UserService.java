package com.wanted.onboarding.service;


import com.wanted.onboarding.dto.SignRequestDto;
import com.wanted.onboarding.dto.SignResponseDto;
import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    /* Get Users by email addr */
    User findUserByEmail(String email) throws UserNotFoundException;
    /* Authentication */
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
    /* Sign Up */
    SignResponseDto signUpNewUser(SignRequestDto user) throws UserNotFoundException;
    /* Sign In */
    SignResponseDto signIn(SignRequestDto user) throws UserNotFoundException;



}
