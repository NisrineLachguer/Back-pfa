package com.lachguer.pfabck.ws.dto;

import com.lachguer.pfabck.model.Role;
import lombok.Data;

public class UserDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Role role; // ADMIN, RECRUITER, CANDIDATE etc.

    public UserDto() {

    }

    public UserDto(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}