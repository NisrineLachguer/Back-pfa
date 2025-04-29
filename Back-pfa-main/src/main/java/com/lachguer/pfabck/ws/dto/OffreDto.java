package com.lachguer.pfabck.ws.dto;

import com.lachguer.pfabck.model.Offre;
import java.util.Date;

public class OffreDto {
    private Long id;
    private Offre.TypeOffre typeOffre;
    private Integer dureeMois;
    private String posteTitre;
    private String description;
    private String localisation;
    private Date dateDebut;
    private Date datePublication;
    private String secteurActivite;
    private String nomEntreprise;
    private String status;

    private Long recruteurId;

    // Getter et Setter
    public Long getRecruteurId() {
        return recruteurId;
    }

    public void setRecruteurId(Long recruteurId) {
        this.recruteurId = recruteurId;
    }

    // Getters et Setters existants...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Offre.TypeOffre getTypeOffre() { return typeOffre; }
    public void setTypeOffre(Offre.TypeOffre typeOffre) { this.typeOffre = typeOffre; }
    public Integer getDureeMois() { return dureeMois; }
    public void setDureeMois(Integer dureeMois) { this.dureeMois = dureeMois; }
    public String getPosteTitre() { return posteTitre; }
    public void setPosteTitre(String posteTitre) { this.posteTitre = posteTitre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDatePublication() { return datePublication; }
    public void setDatePublication(Date datePublication) { this.datePublication = datePublication; }
    public String getSecteurActivite() { return secteurActivite; }
    public void setSecteurActivite(String secteurActivite) { this.secteurActivite = secteurActivite; }
    public String getNomEntreprise() { return nomEntreprise; }
    public void setNomEntreprise(String nomEntreprise) { this.nomEntreprise = nomEntreprise; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}