package com.wanted.onboarding.service;


import com.wanted.onboarding.dto.CustomUserDetails;
import com.wanted.onboarding.dto.SignRequestDto;
import com.wanted.onboarding.dto.SignResponseDto;
import com.wanted.onboarding.entity.Role;
import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.exception.UserNotFoundException;
import com.wanted.onboarding.repo.UserRepository;
import io.jsonwebtoken.impl.lang.UnavailableImplementationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    /* Sign Up */
    @Override
    public SignResponseDto signUp(SignRequestDto signUpDTO) throws Exception{

        // Check if user exists
        userRepository.findUserByEmail(signUpDTO.getEmail()).ifPresent(entity ->
                new UserNotFoundException(String.format("Employee already exist with given email: [%s]",signUpDTO.getEmail()))
        );

        // Create user object
        User user = User.builder()
                .email(signUpDTO.getEmail())
                .password(passwordEncoder.encode(signUpDTO.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        // Save to Repo
        userRepository.save(user);

        // Return with DTO
        return new SignResponseDto(user);
    }

    @Override
    public Boolean signIn(SignRequestDto signInDTO) throws Exception {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInDTO.getEmail(), signInDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return true;
    }

    public Boolean signOut(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(req, res, auth);
            // SecurityContextHolder.clearContext();
            return true;
        }else {
            throw new IllegalStateException("User had not signed in");
        }
    }

    /* Authentication */
    @Override
    public UserDetails loadUserByEmail(String email) throws Exception {
        User member = userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Invalid authentication!")
        );

        return new CustomUserDetails(member);
    }


    /* Get User By Email*/
    @Override
    public SignResponseDto findUserByEmail(String email) throws Exception{
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new UserNotFoundException("There is no registered user by email : " + email)
        );

        return SignResponseDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    /* ADMIN ONLY */
    public List<User> findAllUsers() throws UserNotFoundException {
        List<User> result = userRepository.findAll();
        return result;
    }


}
