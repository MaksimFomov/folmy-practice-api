package com.folmy.folmypractice.facade.impl;

import com.folmy.folmypractice.dto.TeamDetailResponseDto;
import com.folmy.folmypractice.dto.TeamRequestDto;
import com.folmy.folmypractice.dto.TeamSummaryResponseDto;
import com.folmy.folmypractice.facade.TeamFacade;
import com.folmy.folmypractice.mapper.TeamMapper;
import com.folmy.folmypractice.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamFacadeImpl implements TeamFacade {
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    public TeamFacadeImpl(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @Override
    public List<TeamSummaryResponseDto> getAllTeams() {
        return teamMapper.toTeamSummaryResponseDto(
                teamService.getAllTeams()
        );
    }

    @Override
    public TeamDetailResponseDto getTeamById(UUID teamUUID) {
        return teamMapper.toTeamDetailResponseDto(
                teamService.getTeamById(teamUUID)
        );
    }

    @Override
    public TeamSummaryResponseDto createTeam(String teamName) {
        return teamMapper.toTeamSummaryResponseDto(
                teamService.createTeam(teamName)
        );
    }

    @Override
    public TeamSummaryResponseDto updateTeamById(UUID teamUUID, TeamRequestDto newTeamRequestDto) {
        return teamMapper.toTeamSummaryResponseDto(
                teamService.updateTeamById(
                        teamUUID,
                        teamMapper.toEntity(newTeamRequestDto)
                )
        );
    }

    @Override
    public void deleteTeamById(UUID teamUUID) {
        teamService.deleteTeamById(teamUUID);
    }

    @Override
    public void addMembersToTeamById(UUID teamUUID, List<UUID> memberIds) {
        teamService.addMembersToTeamById(teamUUID, memberIds);
    }

    @Override
    public void deleteMembersFromTeamById(UUID teamUUID, List<UUID> memberIds) {
        teamService.deleteMembersFromTeamById(teamUUID, memberIds);
    }

    @Override
    public void addProjectToTeamById(UUID teamUUID, UUID projectUUID) {
        teamService.addProjectToTeamById(teamUUID, projectUUID);
    }

    @Override
    public void deleteProjectFromTeamById(UUID teamUUID) {
        teamService.deleteProjectFromTeamById(teamUUID);
    }
}
