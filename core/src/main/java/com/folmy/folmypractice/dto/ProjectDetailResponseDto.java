package com.folmy.folmypractice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.folmy.folmypractice.model.Project}
 */

public record ProjectDetailResponseDto(UUID id, String title, String briefDescription, String termsOfReference,
                                       LocalDateTime createdAt, LocalDateTime updatedAt) {
}