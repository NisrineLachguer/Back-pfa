package com.lachguer.pfabck.dto;

import com.lachguer.pfabck.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private Role role;

    public UserDto(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}