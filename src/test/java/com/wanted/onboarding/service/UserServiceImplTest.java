package com.wanted.onboarding.service;

import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.repo.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
    void saveUser() {
        User randomUser = new User(1l, "eamil@susp.com", "idontknowpw");
        userServiceImpl.saveUser(randomUser);
        assertEquals(userRepository.findById(1l).isPresent(),true);
    }
}