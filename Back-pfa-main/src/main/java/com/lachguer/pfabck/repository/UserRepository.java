package com.lachguer.pfabck.repository;

import com.lachguer.pfabck.ws.dto.UserDto;
import com.lachguer.pfabck.model.Role;
import com.lachguer.pfabck.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    // Rechercher un utilisateur par ID et par Role
    Optional<User> findByIdAndRole(Long id, Role role);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    // Trouve un utilisateur par son nom d'utilisateur
    Optional<User> findByUsername(String username);

    // Méthode alternative avec requête explicite (optionnelle)
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
    boolean usernameExists(@Param("username") String username);

    // Trouve un utilisateur par son ID avec projection partielle (optionnel)
    @Query("SELECT new com.lachguer.pfabck.ws.dto.UserDto(u.id, u.username, u.role) FROM User u WHERE u.id = :id")
    Optional<UserDto> findUserDtoById(@Param("id") Long id);

    // Vérifie si un utilisateur a un rôle spécifique (optionnel)
    @Query("SELECT u.role FROM User u WHERE u.username = :username")
    Optional<Role> findRoleByUsername(@Param("username") String username);
}