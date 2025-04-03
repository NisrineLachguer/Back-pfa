package com.lachguer.pfabck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private long id;
    private String role;
    private String email;

}