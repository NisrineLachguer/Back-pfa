package com.lachguer.pfabck.ws.converter;

import com.lachguer.pfabck.model.Candidat;
import com.lachguer.pfabck.model.Candidature;
import com.lachguer.pfabck.model.Offre;
import com.lachguer.pfabck.ws.dto.CandidatureDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidatureConverter {

    // Convertir Candidature vers CandidatureDto avec toutes les données
    public CandidatureDto toDto(Candidature candidature) {
        if (candidature == null) {
            return null;
        } else {
            CandidatureDto dto = new CandidatureDto();
            dto.setId(candidature.getId());
            dto.setCandidatId(candidature.getCandidat().getId());
            dto.setOffreId(candidature.getOffre().getId());
            dto.setStatut(candidature.getStatut());

            // Ajout des nouveaux champs
            dto.setDateCandidature(candidature.getDateCandidature());
            dto.setNom(candidature.getNom());
            dto.setPrenom(candidature.getPrenom());
            dto.setEmail(candidature.getCandidat().getEmail()); // Si disponible via candidat
            dto.setTelephone(candidature.getTelephone());
            dto.setFormation(candidature.getFormation());
            dto.setExperience(candidature.getExperience());
            dto.setCompetences(candidature.getCompetences());
            dto.setMotivation(candidature.getMotivation());
            dto.setDisponibilite(candidature.getDisponibilite());
            dto.setOffreTitre(candidature.getOffre().getPosteTitre()); // Utilise getPosteTitre au lieu de getTitre

            return dto;
        }
    }

    // Convertir CandidatureDto vers Candidature
    public Candidature toItem(CandidatureDto dto) {
        if (dto == null) {
            return null;
        } else {
            Candidature candidature = new Candidature();
            candidature.setId(dto.getId());

            // Création des entités associées avec les ID
            if (dto.getCandidatId() != null) {
                Candidat candidat = new Candidat();
                candidat.setId(dto.getCandidatId());
                candidature.setCandidat(candidat);
            }

            if (dto.getOffreId() != null) {
                Offre offre = new Offre();
                offre.setId(dto.getOffreId());
                candidature.setOffre(offre);
            }

            candidature.setStatut(dto.getStatut());

            // Transfert des nouveaux champs
            candidature.setDateCandidature(dto.getDateCandidature());
            candidature.setNom(dto.getNom());
            candidature.setPrenom(dto.getPrenom());
            candidature.setTelephone(dto.getTelephone());
            candidature.setFormation(dto.getFormation());
            candidature.setExperience(dto.getExperience());
            candidature.setCompetences(dto.getCompetences());
            candidature.setMotivation(dto.getMotivation());
            candidature.setDisponibilite(dto.getDisponibilite());

            return candidature;
        }
    }

    // Convertir une liste de Candidature en une liste de CandidatureDto
    public List<CandidatureDto> toDtos(List<Candidature> candidatures) {
        if (candidatures == null) {
            return null;
        } else {
            List<CandidatureDto> dtos = new ArrayList<>();
            for (Candidature candidature : candidatures) {
                dtos.add(toDto(candidature));
            }
            return dtos;
        }
    }

    // Convertir une liste de CandidatureDto en une liste de Candidature
    public List<Candidature> toItems(List<CandidatureDto> dtos) {
        if (dtos == null) {
            return null;
        } else {
            List<Candidature> candidatures = new ArrayList<>();
            for (CandidatureDto dto : dtos) {
                candidatures.add(toItem(dto));
            }
            return candidatures;
        }
    }
}