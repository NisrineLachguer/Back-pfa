package com.lachguer.pfabck.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("recruteurs")
public class Recruteur extends User {
    @Column(name = "entreprise")
    private String entreprise; // Entreprise du recruteur

    @Column(name = "telephone")
    private String telephone;

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
