package com.lachguer.pfabck.service;

import com.lachguer.pfabck.model.Candidat;
import com.lachguer.pfabck.model.Candidature;
import com.lachguer.pfabck.model.Offre;
import com.lachguer.pfabck.repository.CandidatureRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatureService {

    @Autowired
    private CandidatureRepository candidatureRepository;
    @Autowired
    private OffreService offreService;
    @Autowired
    private CandidatService candidatService;

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
    public boolean hasAlreadyApplied(Long candidatId, Long offreId) {
        // Recherche si une candidature existe déjà pour ce candidat et cette offre
        return candidatureRepository.existsByCandidatIdAndOffreId(candidatId, offreId);
    }


    public List<Candidature> findByRecruteurId(Long recruteurId) {
        return candidatureRepository.findByRecruteurIdWithDetails(recruteurId);
    }

    public List<Candidature> findByCandidatId(Long candidatId) {
        return candidatureRepository.findByCandidatId(candidatId);
    }

    @Transactional
    public Candidature updateStatus(Long id, String newStatus) {
        Candidature candidature = findById(id);
        if (candidature != null && Candidature.isValidStatus(newStatus)) {
            candidature.setStatut(newStatus);
            return candidatureRepository.save(candidature);
        }
        return null;
    }
    // Ajouter cette méthode dans CandidatureService.java si elle n'existe pas déjà
    @Transactional
    public List<Candidature> findDetailedCandidaturesByCandidat(Long candidatId) {
        List<Candidature> candidatures = candidatureRepository.findByCandidatId(candidatId);
        // Force le chargement des relations pour éviter le lazy loading
        candidatures.forEach(c -> {
            Hibernate.initialize(c.getOffre());
            // Assurez-vous que toutes les données du formulaire sont chargées
        });
        return candidatures;
    }
}
