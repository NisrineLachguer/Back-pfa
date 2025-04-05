package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.service.OffreService;
import com.lachguer.pfabck.ws.converter.OffreConverter;
import com.lachguer.pfabck.ws.dto.OffreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/")
    public OffreDto updateOffre(@RequestBody OffreDto dto) {
        return offreConverter.toDto(offreService.update(offreConverter.toItem(dto)));
    }

    @DeleteMapping("/{id}")
    public void deleteOffre(@PathVariable Long id) {
        offreService.delete(id);
    }
}
