package com.lachguer.pfabck.ws.converter;

import com.lachguer.pfabck.model.Candidat;
import com.lachguer.pfabck.model.Role;
import com.lachguer.pfabck.model.User;
import com.lachguer.pfabck.ws.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    // Convertir User en UserDto
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());

        // Vérifier si l'utilisateur a le rôle CANDIDATE
        if (user.getRole() == Role.CANDIDATE) {
            // Récupérer telephone et adresse directement depuis l'utilisateur
            userDto.setTelephone(user.getTelephone());  // Utiliser getTelephone() de User (ou de Candidat)
            userDto.setAdresse(user.getAdresse());      // Utiliser getAdresse() de User (ou de Candidat)
        }

        return userDto;
    }

    // Convertir UserDto en User ou Candidat selon le rôle
    public User toItem(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user;

        if (userDto.getRole() == Role.CANDIDATE) {
            Candidat candidat = new Candidat();
            candidat.setTelephone(userDto.getTelephone());
            candidat.setAdresse(userDto.getAdresse());
            user = candidat;
        } else {
            user = new User();
        }

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());

        return user;
    }

}