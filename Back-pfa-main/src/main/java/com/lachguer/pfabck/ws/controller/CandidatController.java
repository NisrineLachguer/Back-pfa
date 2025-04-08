package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.model.Candidat;
import com.lachguer.pfabck.service.CandidatService;
import com.lachguer.pfabck.ws.converter.CandidatConverter;
import com.lachguer.pfabck.ws.dto.CandidatDto;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    @Autowired
    private CandidatConverter converter;

    // Récupérer un candidat par ID
    @GetMapping("/{id}")
    public ResponseEntity<CandidatDto> findById(@PathVariable Long id) {
        Candidat candidat = candidatService.findById(id);
        if (candidat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converter.toDto(candidat));
    }

    // Récupérer un candidat par email
    @GetMapping("/email/{email}")
    public ResponseEntity<CandidatDto> findByEmail(@PathVariable String email) {
        // Find candidate by email
        Candidat candidat = candidatService.findByEmail(email);

        // If candidate doesn't exist, return 404
        if (candidat == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(converter.toDto(candidat));
    }

    // Récupérer tous les candidats
    @GetMapping
    public List<CandidatDto> findAll(Authentication authentication) {
        return converter.toDtos(candidatService.findAll());
    }

    // Ajouter un nouveau candidat
    @PostMapping("/")
    public ResponseEntity<CandidatDto> save(@RequestBody CandidatDto candidatDto) {
        // Convert DTO to entity
        Candidat candidat = converter.toItem(candidatDto);

        // Check if candidate with this email already exists
        Candidat existingCandidat = candidatService.findByEmail(candidat.getEmail());
        if (existingCandidat != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }

        // Save the new candidate
        Candidat savedCandidat = candidatService.save(candidat);

        // Return the saved candidate with 201 Created status
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.toDto(savedCandidat));
    }

    // Mettre à jour un candidat
    @PutMapping("/profile/{id}")
    public ResponseEntity<CandidatDto> updateProfile(@PathVariable Long id, @RequestBody CandidatDto candidatDto) {
        Candidat candidat = candidatService.findById(id);

        if (candidat == null) {
            return ResponseEntity.notFound().build();
        }

        // Convert DTO to entity
        Candidat candidatDetails = converter.toItem(candidatDto);
        // Ensure ID is set correctly
        candidatDetails.setId(id);

        Candidat updatedCandidat = candidatService.updateProfile(id, candidatDetails);
        return ResponseEntity.ok(converter.toDto(updatedCandidat));
    }

    // Supprimer un candidat
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        // Check if candidate exists
        Candidat candidat = candidatService.findById(id);

        // If candidate doesn't exist, return 404
        if (candidat == null) {
            return ResponseEntity.notFound().build();
        }

        // Delete the candidate
        candidatService.delete(id);

        return ResponseEntity.noContent().build();
    }
}