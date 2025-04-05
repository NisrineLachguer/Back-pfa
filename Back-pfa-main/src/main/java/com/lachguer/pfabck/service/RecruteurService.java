package com.lachguer.pfabck.service;

import com.lachguer.pfabck.model.Recruteur;
import com.lachguer.pfabck.repository.RecruteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruteurService {

    @Autowired
    private RecruteurRepository recruteurRepository;

    // Ajouter un recruteur
    public Recruteur save(Recruteur recruteur) {
        return recruteurRepository.save(recruteur);
    }

    // Trouver un recruteur par son ID
    public Recruteur findById(Long id) {
        return recruteurRepository.findById(id).orElse(null);
    }

    // Trouver tous les recruteurs
    public List<Recruteur> findAll() {
        return recruteurRepository.findAll();
    }

    // Mettre à jour un recruteur
    public Recruteur update(Recruteur recruteur) {
        if (recruteurRepository.existsById(recruteur.getId())) {
            return recruteurRepository.save(recruteur);
        }
        return null; // Retourne null si le recruteur n'existe pas
    }

    // Supprimer un recruteur
    public void delete(Long id) {
        if (recruteurRepository.existsById(id)) {
            recruteurRepository.deleteById(id);
        }
    }
}
