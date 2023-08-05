package com.wanted.onboarding.service;

import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Test
    @DisplayName("Test for saving user by inserting random user entity")
    void registerNewUserAccountTest() {
        // GIVEN
        User randomUser = new User(1l, "eamil@susp.com", "idontknowpw");
        // WHEN
        userServiceImpl.registerNewUserAccount(randomUser);
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
    @DisplayName("Check if password has more than 8 chars")
    void checkMinLengthOfPassword(){

        // GIVEN
        String email = "hello@gmail.com";
        String password = "onetwo";
        User unvalid = User.builder()
                .email(email)
                .password(password)
                .build();


        /* THEN :: EXPECTED EXCEPTION */
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            /* WHEN */
            userRepository.save(unvalid);
        });

        /* THEN :: EXPECTED EXCEPTION MESSAGES */
        assertThat(exception).isInstanceOf(Exception.class);
        System.out.println("exception message : "+exception.getMessage());

    }
    @Test
    @DisplayName("Check if password has encrypted")
    void getEncryptedUserTest(){

        // GIVEN
        String email = "hello@gmail.com";
        String password = "random password";
        User fresh = User.builder()
                .email(email)
                .password(password)
                .build();

        // WHEN
        User encryptedUser = userServiceImpl.getEncryptedUser(fresh);

        // THEN
        assertNotEquals(encryptedUser.getPassword(),password);
        System.out.println("encryptedUser.getPassword() : "+encryptedUser.getPassword());
        System.out.println("password : "+password);
    }
}