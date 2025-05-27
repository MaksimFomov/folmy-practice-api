package com.folmy.folmypractice.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link com.folmy.folmypractice.model.Project}
 */

public record ProjectRequestDto(
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 30, message = "Title must be between 3 and 30 characters")
    String title,

    @Size(min = 10, max = 100, message = "Title must be between 10 and 100 characters")
    @NotBlank(message = "Brief description is required")
    String briefDescription,

    @Lob
    @NotBlank(message = "Terms of reference is required")
    String termsOfReference
) {}