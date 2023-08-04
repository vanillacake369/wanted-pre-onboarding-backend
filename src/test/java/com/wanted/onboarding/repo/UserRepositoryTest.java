package com.wanted.onboarding.repo;

import com.wanted.onboarding.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("mysql_test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Email Validation")
    public void testEmailValidation() throws Exception{
        /* GIVEN */
        User unvalid = User.builder().email("worng email").password("random pw").build();

        /* THEN :: EXPECTED EXCEPTION */
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            /* WHEN */
            userRepository.save(unvalid);
        });

        /* THEN :: EXPECTED EXCEPTION MESSAGES */
        org.assertj.core.api.Assertions.assertThat(exception).isInstanceOf(Exception.class);
        System.out.println(exception.getMessage());

    }
}
