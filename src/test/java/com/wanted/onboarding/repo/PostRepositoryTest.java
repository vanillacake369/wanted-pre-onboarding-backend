package com.wanted.onboarding.repo;

import com.wanted.onboarding.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("mysql_test")
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;


    @Test
    @DisplayName("CRUD Test on PostRepository")
    public void testIfConfigWork(){
        // GIVEN
        long id = 1l;
        String contents = "memo";
        postRepository.save(new Post(id,contents));

        // WHEN
        Post savedPost = postRepository.findById(1l).stream().findFirst().orElse(null);

        // THEN
        assertEquals(savedPost.getClass(),Post.class);
        assertEquals(savedPost.getId(),id);
        assertEquals(savedPost.getContents(),contents);
        System.out.println(savedPost.toString());
    }

}
