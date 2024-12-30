package com.example.userservice.controllers;

import com.example.userservice.dtos.LoginRequestDto;
import com.example.userservice.dtos.SignupRequestDto;
import com.example.userservice.dtos.SignupResponseDto;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody SignupRequestDto signupRequestDto){
        User user=userService.signup(signupRequestDto.getUsername(),
                signupRequestDto.getEmail(),
                signupRequestDto.getPassword());
        return SignupResponseDto.form(user);

    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto loginRequestDto){
        return userService.login(loginRequestDto.getEmail(),
                loginRequestDto.getPassword());
    }
}
