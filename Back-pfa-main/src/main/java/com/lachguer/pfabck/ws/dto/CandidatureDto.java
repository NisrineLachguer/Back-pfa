package com.lachguer.pfabck.ws.dto;

import lombok.Data;

@Data
public class CandidatureDto {

    private Long id;
    private Long candidatId;
    private Long offreId;
    private String statut; // Statut de la candidature (en attente, validée, rejetée)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public Long getOffreId() {
        return offreId;
    }

    public void setOffreId(Long offreId) {
        this.offreId = offreId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
