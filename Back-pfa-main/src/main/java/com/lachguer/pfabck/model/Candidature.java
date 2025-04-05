package com.lachguer.pfabck.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "candidatures")
public class Candidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidat_id", nullable = false)
    private Candidat candidat; // Le candidat qui a postulé

    @ManyToOne
    @JoinColumn(name = "offre_id", nullable = false)
    private Offre offre; // L'offre pour laquelle le candidat a postulé

    @Column(nullable = false)
    private Date dateCandidature; // Date à laquelle la candidature a été soumise

    @Column(nullable = false)
    private String statut; // Statut de la candidature (par exemple: "en attente", "validée", "rejetée")

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public Date getDateCandidature() {
        return dateCandidature;
    }

    public void setDateCandidature(Date dateCandidature) {
        this.dateCandidature = dateCandidature;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
