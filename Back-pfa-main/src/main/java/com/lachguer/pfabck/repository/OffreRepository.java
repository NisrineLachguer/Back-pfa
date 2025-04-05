package com.lachguer.pfabck.repository;

import com.lachguer.pfabck.model.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {
    List<Offre> findByRecruteurId(Long recruteurId);

    List<Offre> findByTitreContaining(String titre);
}
