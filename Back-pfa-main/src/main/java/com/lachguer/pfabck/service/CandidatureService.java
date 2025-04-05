package com.lachguer.pfabck.service;

import com.lachguer.pfabck.model.Candidature;
import com.lachguer.pfabck.repository.CandidatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;

    // Ajouter une candidature
    public Candidature save(Candidature candidature) {
        return candidatureRepository.save(candidature);
    }

    // Trouver une candidature par son ID
    public Candidature findById(Long id) {
        return candidatureRepository.findById(id).orElse(null);
    }

    // Trouver toutes les candidatures
    public List<Candidature> findAll() {
        return candidatureRepository.findAll();
    }

    // Mettre à jour une candidature
    public Candidature update(Candidature candidature) {
        if (candidatureRepository.existsById(candidature.getId())) {
            return candidatureRepository.save(candidature);
        }
        return null;
    }

    // Supprimer une candidature
    public void delete(Long id) {
        if (candidatureRepository.existsById(id)) {
            candidatureRepository.deleteById(id);
        }
    }
}
