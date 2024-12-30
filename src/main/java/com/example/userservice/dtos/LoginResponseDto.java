package com.example.userservice.dtos;

import com.example.userservice.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String token;
    private String email;
    private String username;

    public static LoginResponseDto from(Token token) {
        LoginResponseDto loginResponseDto= new LoginResponseDto();
        loginResponseDto.setToken(token.getValue());
        loginResponseDto.setUsername(token.getUser().getName());
        loginResponseDto.setEmail(token.getUser().getEmail());
        return loginResponseDto;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
