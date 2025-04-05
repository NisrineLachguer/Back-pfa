package com.lachguer.pfabck.service;

import com.lachguer.pfabck.model.Candidat;
import com.lachguer.pfabck.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    // Ajouter un nouveau candidat
    public Candidat save(Candidat candidat) {
        return candidatRepository.save(candidat);
    }

    // Trouver un candidat par son email
    public Candidat findByEmail(String email) {
        return candidatRepository.findByEmail(email);
    }

    // Trouver un candidat par son ID
    public Candidat findById(Long id) {
        return candidatRepository.findById(id).orElse(null);
    }

    // Récupérer tous les candidats
    public List<Candidat> findAll() {
        return candidatRepository.findAll();
    }

    // Mettre à jour un candidat
    public Candidat update(Candidat candidat) {
        if (candidatRepository.existsById(candidat.getId())) {
            return candidatRepository.save(candidat);
        }
        return null; // Retourner null si le candidat n'existe pas
    }

    // Supprimer un candidat par son ID
    public void delete(Long id) {
        candidatRepository.deleteById(id);
    }
}
