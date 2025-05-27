package com.folmy.folmypractice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.folmy.folmypractice.model.Project}
 */

public record ProjectSummaryResponseDto(UUID id, String title, String briefDescription, LocalDateTime createdAt) {
}