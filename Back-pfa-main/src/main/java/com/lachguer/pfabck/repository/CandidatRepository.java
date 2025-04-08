package com.lachguer.pfabck.repository;

import com.lachguer.pfabck.model.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {

    Optional<Candidat> findById(Long id);


    Candidat findByEmail(String email);
}
