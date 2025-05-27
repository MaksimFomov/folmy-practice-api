package com.folmy.folmypractice.facade.impl;

import com.folmy.folmypractice.dto.UserDetailResponseDto;
import com.folmy.folmypractice.dto.UserSummaryResponseDto;
import com.folmy.folmypractice.facade.UserFacade;
import com.folmy.folmypractice.mapper.UserMapper;
import com.folmy.folmypractice.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserFacadeImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserSummaryResponseDto> getAllUsers() {
        return userMapper.toUserSummaryResponseDto(
                userService.getAllUsers()
        );
    }

    @Override
    public UserDetailResponseDto getUserByUUID(UUID userUUID) {
        return userMapper.toUserDetailResponseDto(
                userService.getUserByUUID(userUUID)
        );
    }

    @Override
    public void passwordChange(String oldPassword, String newPassword) {
        userService.passwordChange(oldPassword, newPassword);
    }
}
