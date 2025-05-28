package com.folmy.folmypractice.controller;

import com.folmy.folmypractice.dto.ChangePasswordRequestDto;
import com.folmy.folmypractice.dto.UserDetailResponseDto;
import com.folmy.folmypractice.dto.UserSummaryResponseDto;
import com.folmy.folmypractice.facade.UserFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public ResponseEntity<List<UserSummaryResponseDto>> getAllUsers() {
        List<UserSummaryResponseDto> users = userFacade.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{userUUID}")
    public ResponseEntity<UserDetailResponseDto> getUserById(@PathVariable UUID userUUID) {
        UserDetailResponseDto user = userFacade.getUserByUUID(userUUID);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        userFacade.changePassword(changePasswordRequestDto.oldPassword(),
                changePasswordRequestDto.newPassword());
        return ResponseEntity.status(HttpStatus.OK).body("Password successfully changed.");
    }
}
