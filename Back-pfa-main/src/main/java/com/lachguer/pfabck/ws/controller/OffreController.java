package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.service.OffreService;
import com.lachguer.pfabck.ws.converter.OffreConverter;
import com.lachguer.pfabck.ws.dto.OffreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/offres")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @Autowired
    private OffreConverter offreConverter;

    @GetMapping("/{id}")
    public OffreDto getOffreById(@PathVariable Long id) {
        return offreConverter.toDto(offreService.findById(id));
    }

    @GetMapping("/")
    public List<OffreDto> getAllOffres() {
        return offreConverter.toDtos(offreService.findAll());
    }

    @PostMapping("/")
    public OffreDto saveOffre(@RequestBody OffreDto dto) {
        return offreConverter.toDto(offreService.save(offreConverter.toItem(dto)));
    }

    @PutMapping("/{id}")
    public OffreDto updateOffre(@PathVariable Long id, @RequestBody OffreDto dto) {
        dto.setId(id); // Assurer que l'ID est défini correctement
        return offreConverter.toDto(offreService.update(offreConverter.toItem(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteOffre(@PathVariable Long id) {
        offreService.delete(id);
    }

    @GetMapping("/candidat")
    public List<OffreDto> getOffresForCandidat() {
        return offreConverter.toDtos(offreService.findActiveOffres());
    }

    @GetMapping("/secteur/{secteurActivite}")
    public List<OffreDto> getOffresBySecteur(@PathVariable String secteurActivite) {
        return offreConverter.toDtos(offreService.findBySecteurActivite(secteurActivite));
    }


    @PatchMapping("/{id}/status")
    public OffreDto updateOffreStatus(@PathVariable Long id, @RequestBody Map<String, String> status) {
        return offreConverter.toDto(offreService.updateStatus(id, status.get("status")));
    }

    @GetMapping("/recruteur/{recruteurId}")
    public List<OffreDto> getOffresByRecruteur(@PathVariable Long recruteurId) {
        return offreConverter.toDtos(offreService.findByRecruteurId(recruteurId));
    }

    //@PostMapping("/")
    //public OffreDto saveOffre(@RequestBody OffreDto dto, Authentication authentication) {
      //  UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //return offreConverter.toDto(offreService.save(offreConverter.toItem(dto)));
   // }
}