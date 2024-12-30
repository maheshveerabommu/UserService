package com.example.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Token extends BaseModel{
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(Long expiryAt) {
        this.expiryAt = expiryAt;
    }

    @ManyToOne
    private User user;
    private Long expiryAt;
}
