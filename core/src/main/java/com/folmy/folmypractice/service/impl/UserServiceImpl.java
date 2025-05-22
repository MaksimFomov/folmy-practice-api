package com.folmy.folmypractice.service.impl;

import com.folmy.folmypractice.exception.notfound.UserNotFoundException;
import com.folmy.folmypractice.exception.validation.WrongOldPasswordException;
import com.folmy.folmypractice.model.User;
import com.folmy.folmypractice.repository.UserRepository;
import com.folmy.folmypractice.service.UserService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
    public void passwordChange(String oldPassword, String newPassword) {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
        }

        User currentUser = (User) auth.getPrincipal();

        if(currentUser.getPassword().equals(encoder.encode(oldPassword))) {
            currentUser.setPassword(encoder.encode(newPassword));
        } else {
            throw new WrongOldPasswordException("Wrong old password");
        }
    }
}
