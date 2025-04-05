package com.lachguer.pfabck.ws.converter;

import com.lachguer.pfabck.model.Offre;
import com.lachguer.pfabck.ws.dto.OffreDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OffreConverter {

    public OffreDto toDto(Offre offre) {
        if (offre == null) return null;

        OffreDto dto = new OffreDto();
        dto.setId(offre.getId());
        dto.setTitre(offre.getTitre());
        dto.setDescription(offre.getDescription());
        dto.setDatePublication(offre.getDatePublication());
        dto.setRecruteur(offre.getRecruteur()); // Peut être converti aussi en DTO si besoin
        return dto;
    }

    public Offre toItem(OffreDto dto) {
        if (dto == null) return null;

        Offre offre = new Offre();
        offre.setId(dto.getId());
        offre.setTitre(dto.getTitre());
        offre.setDescription(dto.getDescription());
        offre.setDatePublication(dto.getDatePublication());
        offre.setRecruteur(dto.getRecruteur()); // Doit être un objet complet (assure-toi qu’il est bien chargé)
        return offre;
    }

    public List<OffreDto> toDtos(List<Offre> offres) {
        List<OffreDto> dtos = new ArrayList<>();
        for (Offre offre : offres) {
            dtos.add(toDto(offre));
        }
        return dtos;
    }

    public List<Offre> toItems(List<OffreDto> dtos) {
        List<Offre> offres = new ArrayList<>();
        for (OffreDto dto : dtos) {
            offres.add(toItem(dto));
        }
        return offres;
    }
}
