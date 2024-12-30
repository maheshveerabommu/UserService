package com.example.userservice.dtos;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignupResponseDto {
    private String username;
    private String email;
    private List<Role> rolesList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    public static SignupResponseDto form(User user) {
        SignupResponseDto signupResponseDto= new SignupResponseDto();
        signupResponseDto.setUsername(user.getName());
        signupResponseDto.setEmail(user.getEmail());
        signupResponseDto.setRolesList(user.getRoles());
        return signupResponseDto;
    }
}
