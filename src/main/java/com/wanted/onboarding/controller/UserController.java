package com.wanted.onboarding.controller;

import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/members")
    public List<User> findAllMember(){
        return userRepository.findAll();
    }
}
