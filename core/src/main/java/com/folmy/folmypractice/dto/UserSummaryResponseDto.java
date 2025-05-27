package com.folmy.folmypractice.dto;

import com.folmy.folmypractice.enums.Role;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.folmy.folmypractice.model.User}
 */

public record UserSummaryResponseDto(UUID id, String username, String email, Set<Role> roles, String fullName,
                                     LocalDateTime createdAt) {
}