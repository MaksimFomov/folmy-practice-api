package com.folmy.folmypractice.service.impl;

import com.folmy.folmypractice.exception.notfound.TeamNotFoundException;
import com.folmy.folmypractice.model.Team;
import com.folmy.folmypractice.model.User;
import com.folmy.folmypractice.repository.TeamRepository;
import com.folmy.folmypractice.service.ProjectService;
import com.folmy.folmypractice.service.TeamService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ProjectService projectService;

    public TeamServiceImpl(TeamRepository teamRepository, ProjectService projectService) {
        this.teamRepository = teamRepository;
        this.projectService = projectService;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(UUID teamUUID) {
        return teamRepository.findById(teamUUID)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with ID: " + teamUUID));
    }

    @Override
    public Team createTeam(String teamName) {
        String nextTeamNumber = generateNextTeamNumber();

        Team team = new Team();
        team.setName(teamName);
        team.setNumber(nextTeamNumber);

        return teamRepository.save(team);
    }

    private String generateNextTeamNumber() {
        String maxNumber = "1";

        int nextNumber = 1;
        if (maxNumber != null) {
            nextNumber = Integer.parseInt(maxNumber.replace("TEAM-", "")) + 1;
        }

        return String.format("TEAM-%03d", nextNumber);
    }

    @Override
    public Team updateTeamById(UUID teamUUID, Team updatedTeam) {
        Team existingTeam = getTeamById(teamUUID);

        existingTeam.setName(updatedTeam.getName());
        existingTeam.setUpdatedAt(LocalDateTime.now());

        return teamRepository.save(existingTeam);
    }

    @Override
    public void deleteTeamById(UUID teamUUID) {
        teamRepository.delete(getTeamById(teamUUID));
    }

    @Override
    public void addMembersToTeamById(UUID teamUUID, Set<User> members) {
        Team existingTeam = getTeamById(teamUUID);

        if (members == null || members.isEmpty()) {
            return;
        }

        existingTeam.getMembers().addAll(members);

        teamRepository.save(existingTeam);
    }

    @Override
    public void deleteMembersFromTeamById(UUID teamUUID, Set<User> members) {
        Team existingTeam = getTeamById(teamUUID);

        if (members == null || members.isEmpty()) {
            return;
        }

        existingTeam.getMembers().removeAll(members);

        teamRepository.save(existingTeam);
    }

    @Override
    public void addProjectToTeamById(UUID teamUUID, UUID projectUUID) {
        Team existingTeam = getTeamById(teamUUID);
        existingTeam.setProject(projectService.getProjectById(projectUUID));

        teamRepository.save(existingTeam);
    }

    @Override
    public void deleteProjectFromTeamById(UUID teamUUID, UUID projectUUID) {
        Team existingTeam = getTeamById(teamUUID);
        existingTeam.setProject(null);

        teamRepository.save(existingTeam);
    }
}
