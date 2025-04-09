package com.lachguer.pfabck.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "offres")
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_offre", nullable = false)
    private TypeOffre typeOffre;

    @ManyToOne
    @JoinColumn(name = "recruteur_id")
    private User recruteur;

    // Ajoutez getter et setter
    public User getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(User recruteur) {
        this.recruteur = recruteur;
    }
    @Column(name = "duree_mois")
    private Integer dureeMois;

    @Column(name = "poste_titre")
    private String posteTitre;

    @Column(name = "secteur_activite", nullable = false)
    private String secteurActivite;

    @Column(name = "nom_entreprise", nullable = false)
    private String nomEntreprise;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;


    @Column(nullable = false)
    private String localisation;

    @Column(name = "date_debut", nullable = false)
    private Date dateDebut;

    @Column(name = "date_publication", nullable = false)
    private Date datePublication;

    @Column(nullable = false)
    private String status = "active"; // Valeur par défaut

    public enum TypeOffre {
        STAGE("Stage"),
        PRE_EMBAUCHE("Pré-embauche"),
        RECRUTEMENT("Recrutement");

        private final String displayName;

        TypeOffre(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }


    // Getters et Setters existants...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TypeOffre getTypeOffre() { return typeOffre; }
    public void setTypeOffre(TypeOffre typeOffre) { this.typeOffre = typeOffre; }
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

    // Nouveau getter et setter pour nomEntreprise
    public String getNomEntreprise() { return nomEntreprise; }
    public void setNomEntreprise(String nomEntreprise) { this.nomEntreprise = nomEntreprise; }

    // Getter et setter pour status
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}