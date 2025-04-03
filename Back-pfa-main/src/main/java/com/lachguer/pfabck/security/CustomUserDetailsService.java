package com.lachguer.pfabck.security;

import com.lachguer.pfabck.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {  // Changé de username à email
        return userRepository.findByEmail(email)  // Utilise findByEmail au lieu de findByUsername
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}