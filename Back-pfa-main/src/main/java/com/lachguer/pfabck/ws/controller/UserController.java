package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.model.Candidat;
import com.lachguer.pfabck.model.User;
import com.lachguer.pfabck.service.UserService;
import com.lachguer.pfabck.ws.converter.UserConverter;
import com.lachguer.pfabck.ws.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lachguer.pfabck.model.Role;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getCandidateById(@PathVariable Long id) {
        // Trouver un utilisateur avec le rôle CANDIDAT
        User user = userService.findByIdAndRole(id, Role.CANDIDATE);
        // Convertir l'utilisateur en DTO et retourner la réponse
        return ResponseEntity.ok(userConverter.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateCandidate(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            // Afficher les données reçues pour débogage
            System.out.println("Mise à jour du candidat ID: " + id);
            System.out.println("Données reçues: " + userDto.getUsername() + ", " +
                    userDto.getEmail() + ", " + userDto.getTelephone() + ", " + userDto.getAdresse());

            // Mise à jour avec affichage des données
            User updatedUser = userService.updateCandidat(id, userDto);
            UserDto updatedDto = userConverter.toDto(updatedUser);

            System.out.println("Candidat mis à jour: " + updatedDto.getUsername() + ", " +
                    updatedDto.getEmail() + ", " + updatedDto.getTelephone() + ", " + updatedDto.getAdresse());

            return ResponseEntity.ok(updatedDto);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erreur lors de la mise à jour du candidat: " + e.getMessage(), e);
        }
    }


}