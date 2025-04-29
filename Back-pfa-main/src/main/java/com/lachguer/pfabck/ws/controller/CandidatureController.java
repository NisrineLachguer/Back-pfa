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

    @GetMapping("/{id}")
    public CandidatureDto getCandidatureById(@PathVariable Long id) {
        return candidatureConverter.toDto(candidatureService.findById(id));
    }

    @GetMapping("/")
    public List<CandidatureDto> getAllCandidatures() {
        return candidatureConverter.toDtos(candidatureService.findAll());
    }

    @PostMapping("/")
    public CandidatureDto saveCandidature(@RequestBody CandidatureDto candidatureDto) {
        return candidatureConverter.toDto(candidatureService.save(candidatureConverter.toItem(candidatureDto)));
    }

    @PutMapping("/")
    public CandidatureDto updateCandidature(@RequestBody CandidatureDto candidatureDto) {
        return candidatureConverter.toDto(candidatureService.update(candidatureConverter.toItem(candidatureDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteCandidature(@PathVariable Long id) {
        candidatureService.delete(id);
    }
}
