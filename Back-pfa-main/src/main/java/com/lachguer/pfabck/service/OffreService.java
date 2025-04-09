package com.lachguer.pfabck.service;

import com.lachguer.pfabck.model.Offre;
import com.lachguer.pfabck.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreService {

    @Autowired
    private OffreRepository offreRepository;

    public Offre save(Offre offre) {

        return offreRepository.save(offre);
    }

    public Offre findById(Long id) {

        return offreRepository.findById(id).orElse(null);
    }

    public List<Offre> findAll() {

        return offreRepository.findAll();
    }

    public Offre update(Offre offre) {
        if (offreRepository.existsById(offre.getId())) {
            return offreRepository.save(offre);
        }
        return null;
    }

    public void delete(Long id) {
        if (offreRepository.existsById(id)) {
            offreRepository.deleteById(id);
        }
    }

    public List<Offre> findActiveOffres() {
        return offreRepository.findByStatus("active");
    }

    public List<Offre> findBySecteurActivite(String secteurActivite) {
        return offreRepository.findBySecteurActivite(secteurActivite);
    }


    public Offre updateStatus(Long id, String status) {
        Offre offre = offreRepository.findById(id).orElse(null);
        if (offre != null) {
            offre.setStatus(status);
            return offreRepository.save(offre);
        }
        return null;
    }

    public List<Offre> findByRecruteurId(Long recruteurId) {
        return offreRepository.findByRecruteurId(recruteurId);
    }

}