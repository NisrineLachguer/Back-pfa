package com.lachguer.pfabck.ws.converter;

import com.lachguer.pfabck.model.Candidature;
import com.lachguer.pfabck.ws.dto.CandidatureDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidatureConverter {

    // Convertir Candidature vers CandidatureDto
    public CandidatureDto toDto(Candidature candidature) {
        if (candidature == null) {
            return null;
        } else {
            CandidatureDto dto = new CandidatureDto();
            dto.setId(candidature.getId());
            dto.setCandidatId(candidature.getCandidat().getId());
            dto.setOffreId(candidature.getOffre().getId());
            dto.setStatut(candidature.getStatut());
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
            // Vous devrez probablement gérer les entités Candidat et Offre ici
            // candidature.setCandidat(new Candidat(dto.getCandidatId()));
            // candidature.setOffre(new Offre(dto.getOffreId()));
            candidature.setStatut(dto.getStatut());
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
