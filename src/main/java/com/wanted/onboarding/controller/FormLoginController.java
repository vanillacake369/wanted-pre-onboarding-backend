package com.wanted.onboarding.controller;

import com.wanted.onboarding.dto.SignRequestDto;
import com.wanted.onboarding.dto.SignResponseDto;
import com.wanted.onboarding.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/form")
public class FormLoginController {
    private final UserServiceImpl userService;

    @GetMapping({"","/","/users"})
    public @ResponseBody String index(){
        return "index page";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin page";
    }

    @GetMapping("/signIn")
    public @ResponseBody String signIn(){
        return "Logged In !!";
    }

    @GetMapping("/signOut")
    public @ResponseBody String signOut(){
        return "Logged Out";
    }

    @Secured("ROLE_MANAGER")
    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager page";
    }

    @GetMapping("/user")
    public SignResponseDto findByEmail(@RequestParam String emailAddr) throws Exception {
        return userService.findUserByEmail(emailAddr);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<SignResponseDto> signUp(@RequestBody @Valid SignRequestDto signRequestDto) throws Exception{
        return new ResponseEntity<>(userService.signUp(signRequestDto), HttpStatus.OK);
    }

    @PostMapping("/user/signin")
    public ResponseEntity<Boolean> signIn(@RequestBody SignRequestDto signRequestDto) throws Exception{
        return new ResponseEntity<>(userService.signIn(signRequestDto), HttpStatus.OK);
    }
}
