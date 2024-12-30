package com.example.userservice.controllers;

import com.example.userservice.dtos.LoginRequestDto;
import com.example.userservice.dtos.LoginResponseDto;
import com.example.userservice.dtos.SignupRequestDto;
import com.example.userservice.dtos.SignupResponseDto;
import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        Token token= userService.login(loginRequestDto.getEmail(),
                loginRequestDto.getPassword());
        return LoginResponseDto.from(token);
    }

    @GetMapping("/validate/{token}")
    public SignupResponseDto validate(@PathVariable String token){
       User user= userService.validate(token);
       if (user==null ){
            throw new UsernameNotFoundException("Invalid token");
        }
         return SignupResponseDto.form(user);
    }
}
