package com.lachguer.pfabck.repository;

import com.lachguer.pfabck.model.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {
    Candidat findByEmail(String email);
}
