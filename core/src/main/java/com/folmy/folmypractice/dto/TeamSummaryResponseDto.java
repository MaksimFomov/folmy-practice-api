package com.folmy.folmypractice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.folmy.folmypractice.model.Team}
 */

public record TeamSummaryResponseDto(UUID id, String name, String number, UUID projectId, LocalDateTime createdAt) {
}