package com.folmy.folmypractice.facade;

import com.folmy.folmypractice.dto.TeamDetailResponseDto;
import com.folmy.folmypractice.dto.TeamRequestDto;
import com.folmy.folmypractice.dto.TeamSummaryResponseDto;

import java.util.List;
import java.util.UUID;

public interface TeamFacade {
    List<TeamSummaryResponseDto> getAllTeams();

    TeamDetailResponseDto getTeamById(UUID teamUUID);

    TeamSummaryResponseDto createTeam(String teamName);

    TeamSummaryResponseDto updateTeamById(UUID teamUUID, TeamRequestDto newTeamRequestDto);

    void deleteTeamById(UUID teamUUID);

    void addMembersToTeamById(UUID teamUUID, List<UUID> memberIds);

    void deleteMembersFromTeamById(UUID teamUUID, List<UUID> memberIds);

    void addProjectToTeamById(UUID teamUUID, UUID projectUUID);

    void deleteProjectFromTeamById(UUID teamUUID);
}
