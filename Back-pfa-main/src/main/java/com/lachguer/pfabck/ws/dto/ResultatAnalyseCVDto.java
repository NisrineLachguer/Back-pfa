package com.lachguer.pfabck.ws.dto;

import com.lachguer.pfabck.model.Candidature;
import jakarta.persistence.*;

public class ResultatAnalyseCVDto {

    private Long id;

    private Candidature candidature; // La candidature associée à l'analyse de CV

    private String resultat; // "validé" ou "non validé"

    private String commentaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidature getCandidature() {
        return candidature;
    }

    public void setCandidature(Candidature candidature) {
        this.candidature = candidature;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
