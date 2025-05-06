package com.lachguer.pfabck.ws.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CandidatureDto {
    private Long id;
    private Long candidatId;
    private Long offreId;
    private String statut;
    private Date dateCandidature;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String formation;
    private String experience;
    private String competences;
    private String motivation;
    private String disponibilite;
    private String offreTitre; // Si vous voulez inclure le titre de l'offre
    private String localisation;
    private String nomEntreprise;

    // Ajoutez les getters et setters pour ces nouveaux champs

    public void setId(Long id) {
        this.id = id;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public void setOffreId(Long offreId) {
        this.offreId = offreId;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setDateCandidature(Date dateCandidature) {
        this.dateCandidature = dateCandidature;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void setOffreTitre(String offreTitre) {
        this.offreTitre = offreTitre;
    }

    public Long getId() {
        return id;
    }

    public Long getCandidatId() {
        return candidatId;
    }

    public Long getOffreId() {
        return offreId;
    }

    public String getStatut() {
        return statut;
    }

    public Date getDateCandidature() {
        return dateCandidature;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFormation() {
        return formation;
    }

    public String getExperience() {
        return experience;
    }

    public String getCompetences() {
        return competences;
    }

    public String getMotivation() {
        return motivation;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public String getOffreTitre() {
        return offreTitre;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }
}

