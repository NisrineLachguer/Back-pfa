package com.lachguer.pfabck.repository;

import com.lachguer.pfabck.model.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruteurRepository extends JpaRepository<Recruteur, Long> {
    Recruteur findByEmail(String email);
}
