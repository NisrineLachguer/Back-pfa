package com.lachguer.pfabck.service;

import com.lachguer.pfabck.model.Candidat;
import com.lachguer.pfabck.repository.CandidatRepository;
import com.lachguer.pfabck.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatService {
    private static final Logger logger = LoggerFactory.getLogger(CandidatService.class);

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private UserRepository userRepository;

    // Ajouter un nouveau candidat
    public Candidat save(Candidat candidat) {
        logger.info("Saving candidat with email: {}", candidat.getEmail());
        return candidatRepository.save(candidat);
    }

    // Trouver un candidat par son email
    public Candidat findByEmail(String email) {
        logger.info("Finding candidat by email: {}", email);
        Candidat candidat = candidatRepository.findByEmail(email);
        if (candidat == null) {
            logger.warn("No candidat found with email: {}", email);
        }
        return candidat;
    }

    // Trouver un candidat par son ID
    public Candidat findById(Long id) {
        logger.info("Finding candidat by id: {}", id);
        Candidat candidat = candidatRepository.findById(id).orElse(null);
        if (candidat == null) {
            logger.warn("No candidat found with id: {}", id);
        } else {
            logger.info("Found candidat: {}", candidat.getEmail());
        }
        return candidat;
    }

    // Récupérer tous les candidats
    public List<Candidat> findAll() {
        logger.info("Finding all candidats");
        List<Candidat> candidats = candidatRepository.findAll();
        logger.info("Found {} candidats", candidats.size());
        return candidats;
    }

    // Mettre à jour un candidat
    public Candidat updateProfile(Long id, Candidat candidatDetails) {
        logger.info("Updating candidat with id: {}", id);
        Candidat existingCandidat = candidatRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Candidat not found with id: {}", id);
                    return new RuntimeException("Candidat not found with id: " + id);
                });

        // Update candidate fields
        existingCandidat.setUsername(candidatDetails.getUsername());
        // Don't update email as it's used as a unique identifier
        existingCandidat.setTelephone(candidatDetails.getTelephone());
        existingCandidat.setAdresse(candidatDetails.getAdresse());
        existingCandidat.setCvUrl(candidatDetails.getCvUrl());
        existingCandidat.setStatutCV(candidatDetails.getStatutCV());

        // Save updated candidate
        Candidat updated = candidatRepository.save(existingCandidat);
        logger.info("Updated candidat with id: {}", id);
        return updated;
    }

    // Supprimer un candidat par son ID
    public void delete(Long id) {
        logger.info("Deleting candidat with id: {}", id);
        candidatRepository.deleteById(id);
        logger.info("Deleted candidat with id: {}", id);
    }
}