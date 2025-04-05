package com.lachguer.pfabck.ws.converter;

import com.lachguer.pfabck.model.Admin;
import com.lachguer.pfabck.ws.dto.AdminDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminConverter {

    // Convertir Admin vers AdminDto
    public AdminDto toDto(Admin admin) {
        if (admin == null) {
            return null;
        } else {
            AdminDto dto = new AdminDto();
            // Convertir les propriétés héritées de User
            dto.setId(admin.getId());
            dto.setEmail(admin.getEmail());
            dto.setRole(admin.getRole());
            // Convertir les propriétés spécifiques à Admin
            dto.setPermissions(admin.getPermissions());
            return dto;
        }
    }

    // Convertir AdminDto vers Admin
    public Admin toItem(AdminDto dto) {
        if (dto == null) {
            return null;
        } else {
            Admin admin = new Admin();
            // Convertir les propriétés héritées de User
            admin.setId(dto.getId());
            admin.setEmail(dto.getEmail());
            admin.setRole(dto.getRole());
            // Convertir les propriétés spécifiques à Admin
            admin.setPermissions(dto.getPermissions());
            return admin;
        }
    }

    // Convertir une liste de Admin en une liste de AdminDto
    public List<AdminDto> toDtos(List<Admin> admins) {
        if (admins == null) {
            return null;
        } else {
            List<AdminDto> dtos = new ArrayList<>();
            for (Admin admin : admins) {
                dtos.add(toDto(admin));
            }
            return dtos;
        }
    }

    // Convertir une liste de AdminDto en une liste de Admin
    public List<Admin> toItems(List<AdminDto> dtos) {
        if (dtos == null) {
            return null;
        } else {
            List<Admin> admins = new ArrayList<>();
            for (AdminDto dto : dtos) {
                admins.add(toItem(dto));
            }
            return admins;
        }
    }
}
