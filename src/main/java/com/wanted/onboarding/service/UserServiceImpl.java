package com.wanted.onboarding.service;


import com.wanted.onboarding.dto.CustomUserDetails;
import com.wanted.onboarding.dto.SignRequestDto;
import com.wanted.onboarding.dto.SignResponseDto;
import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.exception.UserNotFoundException;
import com.wanted.onboarding.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    /* Sign Up */
    @Override
    public SignResponseDto signUpNewUser(SignRequestDto requestDto) throws UserNotFoundException{

        // Existing User Validation
        userRepository.findUserByEmail(requestDto.getEmail()).orElseThrow(
                ()-> new UserNotFoundException(String.format("Employee already exist with given email: [%s]",requestDto.getEmail()))
        );

        // Encryption
        User encryptedUser = requestDto.getEntity();
        encryptedUser.encodePassword(passwordEncoder);

        // Save to Repo
        userRepository.save(encryptedUser);

        // Return with DTO
        return new SignResponseDto(encryptedUser);
    }

    @Override
    public SignResponseDto signIn(SignRequestDto user) throws UserNotFoundException {
        return null;
    }

    /* Authentication */
    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User member = userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Invalid authentication!")
        );

        return new CustomUserDetails(member);
    }


    /* Get User By Email*/
    @Override
    public User findUserByEmail(String email) throws UserNotFoundException{
        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new UserNotFoundException("There is no registered user by email : " + email)
        );
        return user;
    }



}
