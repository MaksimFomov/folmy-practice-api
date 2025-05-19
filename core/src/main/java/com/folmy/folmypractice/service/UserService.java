package com.folmy.folmypractice.service;

import com.folmy.folmypractice.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();

    User getUserByUUID(UUID userUUID);

    void passwordChange(String oldPassword, String newPassword);
}
