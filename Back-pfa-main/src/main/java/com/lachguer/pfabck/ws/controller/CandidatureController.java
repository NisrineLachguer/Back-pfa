package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.service.CandidatureService;
import com.lachguer.pfabck.ws.converter.CandidatureConverter;
import com.lachguer.pfabck.ws.dto.CandidatureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidatures")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @Autowired
    private CandidatureConverter candidatureConverter;

    // Récupérer une candidature par ID
    @GetMapping("/{id}")
    public CandidatureDto getCandidatureById(@PathVariable Long id) {
        return candidatureConverter.toDto(candidatureService.findById(id));
    }

    // Récupérer toutes les candidatures
    @GetMapping("/")
    public List<CandidatureDto> getAllCandidatures() {
        return candidatureConverter.toDtos(candidatureService.findAll());
    }

    // Ajouter une nouvelle candidature
    @PostMapping("/")
    public CandidatureDto saveCandidature(@RequestBody CandidatureDto candidatureDto) {
        return candidatureConverter.toDto(candidatureService.save(candidatureConverter.toItem(candidatureDto)));
    }

    // Mettre à jour une candidature
    @PutMapping("/")
    public CandidatureDto updateCandidature(@RequestBody CandidatureDto candidatureDto) {
        return candidatureConverter.toDto(candidatureService.update(candidatureConverter.toItem(candidatureDto)));
    }

    // Supprimer une candidature
    @DeleteMapping("/{id}")
    public void deleteCandidature(@PathVariable Long id) {
        candidatureService.delete(id);
    }
}
