package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.service.CandidatService;
import com.lachguer.pfabck.ws.converter.CandidatConverter;
import com.lachguer.pfabck.ws.dto.CandidatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidats")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    @Autowired
    private CandidatConverter candidatConverter;

    // Récupérer un candidat par ID
    @GetMapping("/{id}")
    public CandidatDto getCandidatById(@PathVariable Long id) {
        return candidatConverter.toDto(candidatService.findById(id));
    }

    // Récupérer tous les candidats
    @GetMapping("/")
    public List<CandidatDto> getAllCandidats() {
        return candidatConverter.toDtos(candidatService.findAll());
    }

    // Ajouter un nouveau candidat
    @PostMapping("/")
    public CandidatDto saveCandidat(@RequestBody CandidatDto candidatDto) {
        return candidatConverter.toDto(candidatService.save(candidatConverter.toItem(candidatDto)));
    }

    // Mettre à jour un candidat
    @PutMapping("/")
    public CandidatDto updateCandidat(@RequestBody CandidatDto candidatDto) {
        return candidatConverter.toDto(candidatService.update(candidatConverter.toItem(candidatDto)));
    }

    // Supprimer un candidat
    @DeleteMapping("/{id}")
    public void deleteCandidat(@PathVariable Long id) {
        candidatService.delete(id);
    }
}
