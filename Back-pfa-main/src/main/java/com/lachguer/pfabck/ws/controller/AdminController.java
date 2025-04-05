package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.service.AdminService;
import com.lachguer.pfabck.ws.converter.AdminConverter;
import com.lachguer.pfabck.ws.dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminConverter adminConverter;

    // Récupérer un admin par ID
    @GetMapping("/{id}")
    public AdminDto getAdminById(@PathVariable Long id) {
        return adminConverter.toDto(adminService.findById(id));
    }

    // Récupérer tous les admins
    @GetMapping("/")
    public List<AdminDto> getAllAdmins() {
        return adminConverter.toDtos(adminService.findAll());
    }

    // Ajouter un nouvel admin
    @PostMapping("/")
    public AdminDto saveAdmin(@RequestBody AdminDto adminDto) {
        return adminConverter.toDto(adminService.save(adminConverter.toItem(adminDto)));
    }

    // Mettre à jour un admin
    @PutMapping("/")
    public AdminDto updateAdmin(@RequestBody AdminDto adminDto) {
        return adminConverter.toDto(adminService.update(adminConverter.toItem(adminDto)));
    }

    // Supprimer un admin
    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.delete(id);
    }
}
