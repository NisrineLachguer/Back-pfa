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

    public User findByIdAndRole(Long id, Role role) {
        return userRepository.findByIdAndRole(id, role)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
    }

    @Transactional
    public User updateCandidat(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (user.getRole() == Role.CANDIDATE) {
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());

            user.setTelephone(userDto.getTelephone());
            user.setAdresse(userDto.getAdresse());

            return userRepository.save(user);
        }

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        return userRepository.save(user);
    }


}