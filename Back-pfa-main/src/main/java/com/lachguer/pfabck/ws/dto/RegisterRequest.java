package com.lachguer.pfabck.ws.dto;

import com.lachguer.pfabck.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
    private Role role;
}