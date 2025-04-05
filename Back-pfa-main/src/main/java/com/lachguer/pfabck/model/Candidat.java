package com.lachguer.pfabck.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("candidats")
public class Candidat extends User {
    @Column(name = "cv_url")
    private String cvUrl; // URL du CV du candidat

    @Column(name = "statut_cv")
    private String statutCV; // "validé" ou "non validé"

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "adresse")
    private String adresse;

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }

    public String getStatutCV() {
        return statutCV;
    }

    public void setStatutCV(String statutCV) {
        this.statutCV = statutCV;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    // Getters et Setters
}
