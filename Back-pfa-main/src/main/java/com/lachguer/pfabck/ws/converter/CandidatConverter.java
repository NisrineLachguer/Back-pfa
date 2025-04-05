package com.lachguer.pfabck.ws.converter;

import com.lachguer.pfabck.model.Candidat;
import com.lachguer.pfabck.ws.dto.CandidatDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidatConverter {

    // Convertir Candidat vers CandidatDto
    public CandidatDto toDto(Candidat candidat) {
        if (candidat == null) {
            return null;
        } else {
            CandidatDto dto = new CandidatDto();
            // Convertir les propriétés héritées de User
            dto.setId(candidat.getId());
            dto.setEmail(candidat.getEmail());
            dto.setRole(candidat.getRole());
            // Convertir les propriétés spécifiques à Candidat
            dto.setCvUrl(candidat.getCvUrl());
            dto.setStatutCV(candidat.getStatutCV());
            dto.setTelephone(candidat.getTelephone());
            dto.setAdresse(candidat.getAdresse());
            return dto;
        }
    }

    // Convertir CandidatDto vers Candidat
    public Candidat toItem(CandidatDto dto) {
        if (dto == null) {
            return null;
        } else {
            Candidat candidat = new Candidat();
            // Convertir les propriétés héritées de User
            candidat.setId(dto.getId());
            candidat.setEmail(dto.getEmail());
            candidat.setRole(dto.getRole());
            // Convertir les propriétés spécifiques à Candidat
            candidat.setCvUrl(dto.getCvUrl());
            candidat.setStatutCV(dto.getStatutCV());
            candidat.setTelephone(dto.getTelephone());
            candidat.setAdresse(dto.getAdresse());
            return candidat;
        }
    }

    // Convertir une liste de Candidat en une liste de CandidatDto
    public List<CandidatDto> toDtos(List<Candidat> candidats) {
        if (candidats == null) {
            return null;
        } else {
            List<CandidatDto> dtos = new ArrayList<>();
            for (Candidat candidat : candidats) {
                dtos.add(toDto(candidat));
            }
            return dtos;
        }
    }

    // Convertir une liste de CandidatDto en une liste de Candidat
    public List<Candidat> toItems(List<CandidatDto> dtos) {
        if (dtos == null) {
            return null;
        } else {
            List<Candidat> candidats = new ArrayList<>();
            for (CandidatDto dto : dtos) {
                candidats.add(toItem(dto));
            }
            return candidats;
        }
    }
}
