package com.wanted.onboarding.controller;

import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.repo.UserRepository;
import com.wanted.onboarding.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @GetMapping("/members")
    public List<User> findAllMember(){
        return userRepository.findAll();
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody User freshUser){
//        User freshUser = User.builder().email(email).password(password).build();
        return userService.saveUser(freshUser);
    }
}
