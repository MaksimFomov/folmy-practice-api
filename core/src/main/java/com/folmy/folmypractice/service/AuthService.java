package com.folmy.folmypractice.service;

import com.folmy.folmypractice.model.User;

public interface AuthService {
    void register(User user);

    String login(String login, String password);
}
