package com.lachguer.pfabck.ws.converter;

import com.lachguer.pfabck.model.Recruteur;
import com.lachguer.pfabck.ws.dto.RecruteurDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecruteurConverter {

    // Convertir Recruteur vers RecruteurDto
    public RecruteurDto toDto(Recruteur recruteur) {
        if (recruteur == null) {
            return null;
        } else {
            RecruteurDto dto = new RecruteurDto(recruteur.getId(), recruteur.getEmail(), recruteur.getRole());
            dto.setEntreprise(recruteur.getEntreprise());
            return dto;
        }
    }

    // Convertir RecruteurDto vers Recruteur
    public Recruteur toItem(RecruteurDto dto) {
        if (dto == null) {
            return null;
        } else {
            Recruteur recruteur = new Recruteur();
            recruteur.setId(dto.getId());
            recruteur.setEmail(dto.getEmail());
            recruteur.setRole(dto.getRole());
            recruteur.setEntreprise(dto.getEntreprise());
            return recruteur;
        }
    }

    // Convertir une liste de Recruteur en une liste de RecruteurDto
    public List<RecruteurDto> toDtos(List<Recruteur> recruteurs) {
        if (recruteurs == null) {
            return null;
        } else {
            List<RecruteurDto> dtos = new ArrayList<>();
            for (Recruteur recruteur : recruteurs) {
                dtos.add(toDto(recruteur));
            }
            return dtos;
        }
    }

    // Convertir une liste de RecruteurDto en une liste de Recruteur
    public List<Recruteur> toItems(List<RecruteurDto> dtos) {
        if (dtos == null) {
            return null;
        } else {
            List<Recruteur> recruteurs = new ArrayList<>();
            for (RecruteurDto dto : dtos) {
                recruteurs.add(toItem(dto));
            }
            return recruteurs;
        }
    }
}
