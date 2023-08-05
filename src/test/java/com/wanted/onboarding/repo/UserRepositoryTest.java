package com.wanted.onboarding.repo;

import com.wanted.onboarding.entity.Post;
import com.wanted.onboarding.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("mysql_test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("CRUD Test on UserRepository")
    public void testIfConfigWork(){
        // GIVEN
        long id = 1l;
        String email = "rand@rand.com";
        String pw = "newPw";
        userRepository.save(User.builder().email(email).password(pw).build());

        // WHEN
        User savedUser = userRepository.findById(1l).stream().findFirst().orElse(null);

        // THEN
        assertEquals(savedUser.getClass(),Post.class);
        assertEquals(savedUser.getId(),id);
        assertEquals(savedUser.getEmail(),email);
        assertEquals(savedUser.getPassword(),pw);
        System.out.println(savedUser.toString());
    }
}
