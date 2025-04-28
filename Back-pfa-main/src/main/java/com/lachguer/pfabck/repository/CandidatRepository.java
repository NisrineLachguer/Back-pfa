package com.lachguer.pfabck.repository;

import com.lachguer.pfabck.model.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {

    Optional<Candidat> findById(Long id);
    @Query("SELECT c FROM Candidat c WHERE c.email = :email")
    Candidat findByEmail(@Param("email") String email);

   // Candidat findByEmail(String email);
}
