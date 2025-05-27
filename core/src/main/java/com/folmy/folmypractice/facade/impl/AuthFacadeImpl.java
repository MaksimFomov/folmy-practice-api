package com.folmy.folmypractice.facade.impl;

import com.folmy.folmypractice.dto.RegisterRequestDto;
import com.folmy.folmypractice.facade.AuthFacade;
import com.folmy.folmypractice.mapper.UserMapper;
import com.folmy.folmypractice.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthFacadeImpl implements AuthFacade {
    private final AuthService authService;
    private final UserMapper userMapper;

    public AuthFacadeImpl(AuthService authService, UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @Override
    public void register(RegisterRequestDto registerRequestDto) {
        authService.register(
                userMapper.toEntity(registerRequestDto)
        );
    }

    @Override
    public String login(String login, String password) {
        return authService.login(login, password);
    }
}
