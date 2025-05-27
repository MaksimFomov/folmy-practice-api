package com.folmy.folmypractice.dto;

import com.folmy.folmypractice.enums.LevelInDevelopment;
import com.folmy.folmypractice.enums.Role;
import com.folmy.folmypractice.model.Team;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.folmy.folmypractice.model.User}
 */

public record UserDetailResponseDto(UUID id, String username, String email, Set<Role> roles, String fullName,
                                    String aboutMe, List<String> directionsInDevelopment, List<String> skills,
                                    LevelInDevelopment levelInDevelopment, int hoursAvailablePerWeek, ZoneId timeZone,
                                    LocalDateTime createdAt, LocalDateTime updatedAt, List<Team> teams) {
}