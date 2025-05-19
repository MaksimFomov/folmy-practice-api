package com.folmy.folmypractice.service.impl;

import com.folmy.folmypractice.exception.conflict.NameAlreadyExistsException;
import com.folmy.folmypractice.exception.conflict.TeamIsTiedException;
import com.folmy.folmypractice.exception.notfound.TeamNotFoundException;
import com.folmy.folmypractice.exception.validation.EmptyMemberSet;
import com.folmy.folmypractice.model.Project;
import com.folmy.folmypractice.model.Team;
import com.folmy.folmypractice.model.User;
import com.folmy.folmypractice.repository.TeamRepository;
import com.folmy.folmypractice.service.ProjectService;
import com.folmy.folmypractice.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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

    @Transactional(readOnly = true)
    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Team getTeamById(UUID teamUUID) {
        return teamRepository.findById(teamUUID)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with ID: " + teamUUID));
    }

    @Transactional
    @Override
    public Team createTeam(String teamName) {
        if (teamRepository.existsByName(teamName)) {
            throw new NameAlreadyExistsException("A command named «" + teamName + "» already exists");
        }

        Team team = Team.builder()
                .name(teamName)
                .number(generateNextTeamNumber())
                .build();

        return teamRepository.save(team);
    }

    private String generateNextTeamNumber() {
        final int CODE_LENGTH = 4;
        String last = teamRepository.findMaxTeamCode()
                .orElse(String.join("", Collections.nCopies(CODE_LENGTH, "0")));
        int value = Integer.parseInt(last, 36) + 1;
        String next = Integer.toString(value, 36).toUpperCase();

        return String.format("%" + CODE_LENGTH + "s", next).replace(' ', '0');
    }

    @Transactional
    @Override
    public Team updateTeamById(UUID teamUUID, Team updatedTeam) {
        Team existingTeam = getTeamById(teamUUID);
        String newName = updatedTeam.getName();

        if (!newName.equals(existingTeam.getName()) && teamRepository.existsByName(newName)) {
            throw new NameAlreadyExistsException("A command named «" + newName + "» already exists");
        }

        existingTeam.setName(newName);

        return existingTeam;
    }

    @Transactional
    @Override
    public void deleteTeamById(UUID teamUUID) {
        teamRepository.delete(getTeamById(teamUUID));
    }

    @Transactional
    @Override
    public void addMembersToTeamById(UUID teamUUID, Set<User> members) {
        if(CollectionUtils.isEmpty(members)) {
            throw new EmptyMemberSet("The set of participants cannot be empty");
        }

        Team existingTeam = getTeamById(teamUUID);

        members.stream()
                .filter(member -> existingTeam.getMembers().add(member))
                .forEach(member -> member.getTeams().add(existingTeam));
    }

    @Transactional
    @Override
    public void deleteMembersFromTeamById(UUID teamUUID, Set<User> members) {
        if(CollectionUtils.isEmpty(members)) {
            throw new EmptyMemberSet("The set of participants cannot be empty");
        }

        Team existingTeam = getTeamById(teamUUID);

        members.stream()
                .filter(existingTeam.getMembers()::remove)
                .forEach(member -> member.getTeams().remove(existingTeam));
    }

    @Transactional
    @Override
    public void addProjectToTeamById(UUID teamUUID, UUID projectUUID) {
        Team existingTeam = getTeamById(teamUUID);
        Project existingProject = projectService.getProjectById(projectUUID);

        if (existingProject.equals(existingTeam.getProject())) {
            return;
        }
        if (existingTeam.getProject() != null) {
            throw new TeamIsTiedException("The team is already attached to the project " + existingTeam.getProject().getId());
        }

        existingTeam.setProject(existingProject);
    }

    @Transactional
    @Override
    public void deleteProjectFromTeamById(UUID teamUUID) {
        Team existingTeam = getTeamById(teamUUID);

        Project project = existingTeam.getProject();
        if (project == null) {
            return;
        }

        existingTeam.setProject(null);
    }
}
