package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.service.ResultatAnalyseCVService;
import com.lachguer.pfabck.ws.converter.ResultatAnalyseCVConverter;
import com.lachguer.pfabck.ws.dto.ResultatAnalyseCVDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analyse-cv")
public class ResultatAnalyseCVController {

    @Autowired
    private ResultatAnalyseCVService service;

    @Autowired
    private ResultatAnalyseCVConverter converter;

    @GetMapping("/{id}")
    public ResultatAnalyseCVDto getById(@PathVariable Long id) {
        return converter.toDto(service.findById(id));
    }

    @GetMapping("/")
    public List<ResultatAnalyseCVDto> getAll() {
        return converter.toDtos(service.findAll());
    }

    @PostMapping("/")
    public ResultatAnalyseCVDto save(@RequestBody ResultatAnalyseCVDto dto) {
        return converter.toDto(service.save(converter.toItem(dto)));
    }

    @PutMapping("/")
    public ResultatAnalyseCVDto update(@RequestBody ResultatAnalyseCVDto dto) {
        return converter.toDto(service.update(converter.toItem(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
