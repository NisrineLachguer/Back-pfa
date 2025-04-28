package com.lachguer.pfabck.repository;

import com.lachguer.pfabck.model.Candidature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    List<Candidature> findByCandidatId(Long candidatId);

    List<Candidature> findByOffreId(Long offreId);

    Candidature findByCandidatIdAndOffreId(Long candidatId, Long offreId);
    boolean existsByCandidatIdAndOffreId(Long candidatId, Long offreId);
}
