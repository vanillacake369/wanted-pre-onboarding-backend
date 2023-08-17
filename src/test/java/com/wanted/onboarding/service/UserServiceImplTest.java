package com.wanted.onboarding.service;

import com.wanted.onboarding.dto.SignRequestDto;
import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("mysql_test")
public class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Test for saving user by inserting random user entity")
    void registerNewUserAccountTest() throws Exception {
        // GIVEN
        String email = "eamil@susp.com";
        String password = "idontknowpw";
        SignRequestDto signRequestDto = SignRequestDto.builder()
                .email(email)
                .password(password)
                .build();
        // WHEN
        userServiceImpl.signUp(signRequestDto);
        // THEN
        assertEquals(userRepository.findById(1l).isPresent(),true);
    }
    @Test
    @DisplayName("At(@) Sign Validation")
    void checkAtSignInEmailField() {
        /* GIVEN */
        User unvalid = User.builder()
                .email("worng email")
                .password("random pw")
                .build();

        /* THEN :: EXPECTED EXCEPTION */
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            /* WHEN */
            userRepository.save(unvalid);
        });

        /* THEN :: EXPECTED EXCEPTION MESSAGES */
        assertThat(exception).isInstanceOf(Exception.class);
        System.out.println(exception.getMessage());
    }

    @Test
    @DisplayName("Check if password has encrypted")
    void getEncryptedUserTest(){

        // GIVEN
        String rawPassword = "randomPassword**123";

        // WHEN
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // THEN
        assertNotEquals(rawPassword,encodedPassword);
        assertTrue(passwordEncoder.matches(rawPassword,encodedPassword));
    }
}