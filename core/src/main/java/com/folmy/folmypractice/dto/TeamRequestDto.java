package com.folmy.folmypractice.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for {@link com.folmy.folmypractice.model.Team}
 */
public record TeamRequestDto(
        @NotBlank(message = "Name is required")
        String name
) {}