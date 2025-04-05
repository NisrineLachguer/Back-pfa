package com.lachguer.pfabck.ws.dto;

import com.lachguer.pfabck.model.Recruteur;
import jakarta.persistence.*;

import java.util.Date;

public class OffreDto {

    private Long id;
    private String titre; // Titre de l'offre
    private String description; // Description détaillée de l'offre
    private Date datePublication; // Date de publication de l'offre
    @JoinColumn(name = "recruteur_id", nullable = false)
    private Recruteur recruteur; // Le recruteur qui a posté l'offre

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public Recruteur getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(Recruteur recruteur) {
        this.recruteur = recruteur;
    }
}
