package com.lachguer.pfabck.ws.dto;

import com.lachguer.pfabck.model.Role;

public class RecruteurDto extends UserDto {

    private String entreprise;

    public RecruteurDto(Long id, String email, Role role) {
        super(id, email, role);
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }
}
