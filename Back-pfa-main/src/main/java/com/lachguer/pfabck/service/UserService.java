package com.lachguer.pfabck.service;

import com.lachguer.pfabck.model.Candidat;
import com.lachguer.pfabck.model.Role;
import com.lachguer.pfabck.model.User;
import com.lachguer.pfabck.repository.UserRepository;
import com.lachguer.pfabck.ws.dto.UserDto;
import com.lachguer.pfabck.ws.converter.UserConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    // Trouver un utilisateur par ID et rôle
    public User findByIdAndRole(Long id, Role role) {
        return userRepository.findByIdAndRole(id, role)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    // Méthode de mise à jour de l'utilisateur
    @Transactional
    public User updateCandidat(Long id, UserDto userDto) {
        // Récupérer l'utilisateur existant de la base de données
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifier si l'utilisateur a le rôle CANDIDATE
        if (user.getRole() == Role.CANDIDATE) {
            // Mettre à jour tous les champs pour les utilisateurs de type CANDIDATE
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());

            // Mettre à jour téléphone et adresse uniquement pour les candidats
            user.setTelephone(userDto.getTelephone());
            user.setAdresse(userDto.getAdresse());

            // Sauvegarder l'utilisateur mis à jour dans la base de données
            return userRepository.save(user);
        }

        // Pour les autres types d'utilisateurs (ADMIN, RECRUITER), on ne met à jour que les champs communs
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        return userRepository.save(user);
    }


}