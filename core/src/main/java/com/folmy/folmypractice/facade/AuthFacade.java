package com.folmy.folmypractice.facade;

import com.folmy.folmypractice.dto.RegisterRequestDto;

public interface AuthFacade {
    void register(RegisterRequestDto registerRequestDto);

    String login(String login, String password);
}
