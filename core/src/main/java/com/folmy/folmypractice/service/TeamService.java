package com.folmy.folmypractice.service;

import com.folmy.folmypractice.model.Team;
import com.folmy.folmypractice.model.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TeamService {
    List<Team> getAllTeams();

    Team getTeamById(UUID teamUUID);

    Team createTeam(String teamName);

    Team updateTeamById(UUID teamUUID, Team updatedTeam);

    void deleteTeamById(UUID teamUUID);

    void addMembersToTeamById(UUID teamUUID, Set<User> members);

    void deleteMembersFromTeamById(UUID teamUUID, Set<User> members);

    void addProjectToTeamById(UUID teamUUID, UUID projectUUID);

    void deleteProjectFromTeamById(UUID teamUUID, UUID projectUUID);
}
