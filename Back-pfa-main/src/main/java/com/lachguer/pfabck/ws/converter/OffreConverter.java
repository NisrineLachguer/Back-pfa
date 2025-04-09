package com.lachguer.pfabck.ws.converter;

import com.lachguer.pfabck.model.Offre;
import com.lachguer.pfabck.model.User;
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
        dto.setTypeOffre(offre.getTypeOffre());
        dto.setDureeMois(offre.getDureeMois());
        dto.setPosteTitre(offre.getPosteTitre());
        dto.setDescription(offre.getDescription());
        dto.setLocalisation(offre.getLocalisation());
        dto.setDateDebut(offre.getDateDebut());
        dto.setDatePublication(offre.getDatePublication());
        dto.setSecteurActivite(offre.getSecteurActivite());
        dto.setNomEntreprise(offre.getNomEntreprise());
        dto.setStatus(offre.getStatus());
        return dto;
    }

    public Offre toItem(OffreDto dto) {
        if (dto == null) return null;

        Offre offre = new Offre();
        offre.setId(dto.getId());
        offre.setId(dto.getId());
        offre.setTypeOffre(dto.getTypeOffre());
        offre.setDureeMois(dto.getDureeMois());
        offre.setPosteTitre(dto.getPosteTitre());
        offre.setDescription(dto.getDescription());
        offre.setLocalisation(dto.getLocalisation());
        offre.setDateDebut(dto.getDateDebut());
        offre.setDatePublication(dto.getDatePublication());
        offre.setSecteurActivite(dto.getSecteurActivite());
        offre.setNomEntreprise(dto.getNomEntreprise());
        offre.setStatus(dto.getStatus());
        if (dto.getRecruteurId() != null) {
            User recruteur = new User();
            recruteur.setId(dto.getRecruteurId());
            offre.setRecruteur(recruteur);
        }
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