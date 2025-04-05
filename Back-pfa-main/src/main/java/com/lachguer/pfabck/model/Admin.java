package com.lachguer.pfabck.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("admins")
public class Admin extends User {
    // Attributs spécifiques à l'admin peuvent être ajoutés ici
    @Column(name = "permissions")
    private String permissions; // Par exemple, les permissions de l'admin

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
