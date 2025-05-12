package com.folmy.folmypractice.service.impl;

import com.folmy.folmypractice.configuration.JwtUtils;
import com.folmy.folmypractice.enums.Role;
import com.folmy.folmypractice.model.User;
import com.folmy.folmypractice.repository.UserRepository;
import com.folmy.folmypractice.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(AuthenticationManager authManager, UserRepository userRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User with this name already exists");
        }
        User newUser = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .roles(Set.of(Role.ROLE_USER))
                .fullName(user.getFullName())
                .aboutMe(user.getAboutMe())
                .directionsInDevelopment(user.getDirectionsInDevelopment())
                .skills(user.getSkills())
                .levelInDevelopment(user.getLevelInDevelopment())
                .hoursAvailablePerWeek(user.getHoursAvailablePerWeek())
                .timeZone(user.getTimeZone())
                .build();
        userRepository.save(newUser);
    }

    @Override
    public String login(String login, String password) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login, password));
        var principal = (org.springframework.security.core.userdetails.User)
                auth.getPrincipal();
        return jwtUtils.generateToken(principal);
    }
}
