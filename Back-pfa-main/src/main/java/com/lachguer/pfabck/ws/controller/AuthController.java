package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.model.Role;
import com.lachguer.pfabck.model.User;
import com.lachguer.pfabck.repository.UserRepository;
import com.lachguer.pfabck.security.JwtTokenProvider;
import com.lachguer.pfabck.service.AuthService;
import com.lachguer.pfabck.ws.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthService authService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider tokenProvider) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    // Méthode pour réinitialiser le mot de passe admin (à désactiver en production)


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Tentative de login avec email: " + loginRequest.getEmail());

            User user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> {
                        System.out.println("Email non trouvé: " + loginRequest.getEmail());
                        return new UsernameNotFoundException("User not found");
                    });

            System.out.println("User trouvé: " + user.getEmail());
            System.out.println("Mot de passe en base: " + user.getPassword());

            // Vérification manuelle du mot de passe
            boolean passwordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
            System.out.println("Le mot de passe correspond ? " + passwordMatch);

            if (!passwordMatch) {
                throw new BadCredentialsException("Invalid credentials");
            }

            String token = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

            return ResponseEntity.ok(new LoginResponse(
                    token,
                    user.getId(),
                    user.getRole().name(),
                    user.getEmail()
            ));
        } catch (BadCredentialsException e) {
            System.out.println("Mauvais credentials pour: " + loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Email ou mot de passe incorrect"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("User not found"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getRole() == Role.ADMIN) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Admin registration not allowed"));
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("Email is already taken"));
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Ensure password is encoded
        user.setRole(registerRequest.getRole());

        User savedUser = userRepository.save(user);

        return ResponseEntity.ok(new UserDto(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole()
        ));
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7); // Enlève "Bearer "
            String username = tokenProvider.getUsernameFromJWT(jwt);

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return ResponseEntity.ok(new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getRole()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid token"));
        }
    }
}