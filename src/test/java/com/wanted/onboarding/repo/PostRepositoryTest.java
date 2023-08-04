package com.wanted.onboarding.repo;

import com.wanted.onboarding.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("mysql_test")
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;


    @Test
    @DisplayName("Can I connect to MySQL ?")
    public void testIfConfigWork(){
        Post savedPost = postRepository.save(new Post(1l, "cont"));
        System.out.println(savedPost.toString());
        assertEquals(savedPost.getId(), 1l);
    }

}
