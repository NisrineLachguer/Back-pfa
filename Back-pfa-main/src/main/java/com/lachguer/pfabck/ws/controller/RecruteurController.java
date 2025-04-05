package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.service.RecruteurService;
import com.lachguer.pfabck.ws.converter.RecruteurConverter;
import com.lachguer.pfabck.ws.dto.RecruteurDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recruteurs")
public class RecruteurController {

    @Autowired
    private RecruteurService recruteurService;

    @Autowired
    private RecruteurConverter recruteurConverter;

    // Récupérer un recruteur par ID
    @GetMapping("/{id}")
    public RecruteurDto getRecruteurById(@PathVariable Long id) {
        return recruteurConverter.toDto(recruteurService.findById(id));
    }

    // Récupérer tous les recruteurs
    @GetMapping("/")
    public List<RecruteurDto> getAllRecruteurs() {
        return recruteurConverter.toDtos(recruteurService.findAll());
    }

    // Ajouter un nouveau recruteur
    @PostMapping("/")
    public RecruteurDto saveRecruteur(@RequestBody RecruteurDto recruteurDto) {
        return recruteurConverter.toDto(recruteurService.save(recruteurConverter.toItem(recruteurDto)));
    }

    // Mettre à jour un recruteur
    @PutMapping("/")
    public RecruteurDto updateRecruteur(@RequestBody RecruteurDto recruteurDto) {
        return recruteurConverter.toDto(recruteurService.update(recruteurConverter.toItem(recruteurDto)));
    }

    // Supprimer un recruteur
    @DeleteMapping("/{id}")
    public void deleteRecruteur(@PathVariable Long id) {
        recruteurService.delete(id);
    }
}
