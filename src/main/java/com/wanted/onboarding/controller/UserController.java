package com.wanted.onboarding.controller;

import com.wanted.onboarding.dto.SignRequestDto;
import com.wanted.onboarding.dto.SignResponseDto;
import com.wanted.onboarding.entity.User;
import com.wanted.onboarding.repo.UserRepository;
import com.wanted.onboarding.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;


    @GetMapping("/hi")
    public String hello(){
        return "hi";
    }

    /* ADMIN ONLY */
    @GetMapping("/users")
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/user")
    public SignResponseDto findByEmail(@RequestParam String emailAddr) throws Exception {
        return userService.findUserByEmail(emailAddr);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignResponseDto> signUp(@RequestBody @Valid SignRequestDto signRequestDto) throws Exception{
        return new ResponseEntity<>(userService.signUp(signRequestDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public boolean signIn(@RequestBody @Valid SignRequestDto signRequestDto) throws Exception{
        return userService.signIn(signRequestDto);
    }


}
