package com.lachguer.pfabck.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resultats_analyse_cv")
public class ResultatAnalyseCV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidature_id", nullable = false)
    private Candidature candidature; // La candidature associée à l'analyse de CV

    @Column(nullable = false)
    private String resultat; // "validé" ou "non validé"

    @Column(nullable = false)
    private String commentaire; // Commentaire de l'analyse (par exemple, "manque d'expérience")

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
