package com.lachguer.pfabck.service;

import com.lachguer.pfabck.model.Admin;
import com.lachguer.pfabck.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Ajouter un nouvel admin
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    // Trouver un admin par son ID
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    // Trouver tous les admins
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    // Mettre à jour un admin
    public Admin update(Admin admin) {
        if (adminRepository.existsById(admin.getId())) {
            return adminRepository.save(admin);
        }
        return null; // Retourne null si l'admin n'existe pas
    }

    // Supprimer un admin
    public void delete(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
        }
    }
}
