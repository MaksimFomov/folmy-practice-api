package com.folmy.folmypractice.service.impl;

import com.folmy.folmypractice.exception.notfound.UserNotFoundException;
import com.folmy.folmypractice.exception.validation.WrongOldPasswordException;
import com.folmy.folmypractice.model.DomainUserDetails;
import com.folmy.folmypractice.model.User;
import com.folmy.folmypractice.repository.UserRepository;
import com.folmy.folmypractice.service.UserService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByUUID(UUID userUUID) {
        return userRepository.findById(userUUID)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID" + userUUID));
    }

    @Transactional
    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
        }

        DomainUserDetails currentUser = (DomainUserDetails) auth.getPrincipal();
        UUID userUUID = currentUser.getDomainUser().getId();

        User user = getUserByUUID(userUUID);

        if (!encoder.matches(oldPassword, currentUser.getPassword())) {
            throw new WrongOldPasswordException("Wrong old password");
        }

        user.setPassword(encoder.encode(newPassword));

        UserDetails updatedDetails = new DomainUserDetails(user);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                updatedDetails,
                updatedDetails.getPassword(),
                updatedDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
