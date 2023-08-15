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

    @GetMapping("/helloworld")
    public String getHelloWorld(){
        return "hello world";
    }

    @GetMapping("/users")
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public User findByEmail(@PathVariable String emailAddr){
        return userService.findUserByEmail(emailAddr);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignResponseDto> signUp(@RequestBody @Valid SignRequestDto signRequestDto) throws Exception{
        return new ResponseEntity<>(userService.signUpNewUser(signRequestDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<SignResponseDto> signIn(@RequestBody @Valid SignRequestDto signRequestDto) throws Exception{
        return new ResponseEntity<>(userService.signUpNewUser(signRequestDto), HttpStatus.OK);
    }


}
