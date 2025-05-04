package com.lachguer.pfabck.repository;

import com.lachguer.pfabck.model.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByCandidatId(Long candidatId);

    Candidature findByCandidatIdAndOffreId(Long candidatId, Long offreId);
    boolean existsByCandidatIdAndOffreId(Long candidatId, Long offreId);
    List<Candidature> findByOffreId(Long offreId);
    @Query("SELECT c FROM Candidature c " +
            "JOIN FETCH c.candidat cand " +
            "JOIN FETCH c.offre o " +
            "WHERE o.recruteur.id = :recruteurId")
    List<Candidature> findByRecruteurIdWithDetails(@Param("recruteurId") Long recruteurId);
}

