package com.folmy.folmypractice.facade;

import com.folmy.folmypractice.dto.UserDetailResponseDto;
import com.folmy.folmypractice.dto.UserSummaryResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserFacade {
    List<UserSummaryResponseDto> getAllUsers();

    UserDetailResponseDto getUserByUUID(UUID userUUID);

    void passwordChange(String oldPassword, String newPassword);
}
