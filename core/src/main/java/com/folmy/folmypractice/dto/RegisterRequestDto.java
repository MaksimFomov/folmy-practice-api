package com.folmy.folmypractice.dto;

import com.folmy.folmypractice.enums.LevelInDevelopment;
import jakarta.validation.constraints.*;

import java.time.ZoneId;
import java.util.List;

/**
 * DTO for {@link com.folmy.folmypractice.model.User}
 */

public record RegisterRequestDto(
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    String username,

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password,

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be at most 100 characters")
    String fullName,

    @Size(max = 500, message = "About Me must be at most 500 characters")
    String aboutMe,

    @NotNull(message = "Directions must be provided")
    @Size(min = 1, message = "At least one direction is required")
    List<@NotBlank(message = "Direction cannot be blank") String> directionsInDevelopment,

    @NotNull(message = "Skills must be provided")
    @Size(min = 1, message = "At least one skill is required")
    List<@NotBlank(message = "Skill cannot be blank") String> skills,

    @NotNull(message = "Development level is required")
    LevelInDevelopment levelInDevelopment,

    @NotNull(message = "Available hours per week is required")
    @Min(value = 0, message = "Hours per week cannot be negative")
    @Max(value = 168, message = "Hours per week cannot exceed 168")
    Integer hoursAvailablePerWeek,

    @NotNull(message = "Time zone is required")
    ZoneId timeZone
) {}
