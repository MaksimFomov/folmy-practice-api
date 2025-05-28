package com.folmy.folmypractice.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record AddDeleteMembersToTeamRequestDto(
        @NotEmpty
        List<UUID> memberIds
) { }
