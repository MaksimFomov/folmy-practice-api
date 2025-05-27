package com.folmy.folmypractice.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.folmy.folmypractice.model.Team}
 */
public record TeamDetailResponseDto(UUID id, String name, String number, ProjectSummaryResponseDto project, List<UserSummaryResponseDto> members,
                                    LocalDateTime createdAt, LocalDateTime updatedAt) {
}