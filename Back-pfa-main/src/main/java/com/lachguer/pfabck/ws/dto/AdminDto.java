package com.lachguer.pfabck.ws.dto;

import com.lachguer.pfabck.model.Role;

public class AdminDto extends UserDto {

    private String permissions; // Permissions spécifiques à l'admin

    // Constructeur par défaut
    public AdminDto() {
        super();
    }

    // Constructeur avec paramètres
    public AdminDto(Long id, String email, Role role, String permissions) {
        super(id, email, role);  // Appel du constructeur de la classe parente UserDto
        this.permissions = permissions;
    }

    // Getter et Setter pour 'permissions'
    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
