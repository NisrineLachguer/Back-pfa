package com.lachguer.pfabck.ws.dto;

import com.lachguer.pfabck.model.Role;

public class CandidatDto extends UserDto {

    private String cvUrl; // URL du CV du candidat

    private String statutCV; // "validé" ou "non validé"

    private String telephone;

    private String adresse;

    public CandidatDto() {
        super();
    }

    public CandidatDto(Long id, String email, Role role) {
        super(id, email, role);
    }


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
}