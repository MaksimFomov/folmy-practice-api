package com.folmy.folmypractice.controller;

import com.folmy.folmypractice.dto.AddDeleteMembersToTeamRequestDto;
import com.folmy.folmypractice.dto.TeamDetailResponseDto;
import com.folmy.folmypractice.dto.TeamRequestDto;
import com.folmy.folmypractice.dto.TeamSummaryResponseDto;
import com.folmy.folmypractice.facade.TeamFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamFacade teamFacade;

    public TeamController(TeamFacade teamFacade) {
        this.teamFacade = teamFacade;
    }

    @GetMapping
    public ResponseEntity<List<TeamSummaryResponseDto>> getAllTeams() {
        List<TeamSummaryResponseDto> teams = teamFacade.getAllTeams();
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDetailResponseDto> getTeamById(@PathVariable UUID teamId) {
        TeamDetailResponseDto team = teamFacade.getTeamById(teamId);
        return ResponseEntity.status(HttpStatus.OK).body(team);
    }

    @PostMapping
    public ResponseEntity<TeamSummaryResponseDto> createTeam(@Valid @RequestBody TeamRequestDto teamRequestDto) {
        TeamSummaryResponseDto newTeam = teamFacade.createTeam(teamRequestDto.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(newTeam);
    }

    @PutMapping("/{updatedTeamUUID}")
    public ResponseEntity<TeamSummaryResponseDto> updateTeamById(@PathVariable UUID updatedTeamUUID,
                                                                 @Valid @RequestBody TeamRequestDto teamRequestDto) {
        TeamSummaryResponseDto updatedTeam = teamFacade.updateTeamById(updatedTeamUUID, teamRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedTeam);
    }

    @DeleteMapping("/{deletedTeamUUID}")
    public ResponseEntity<String> deleteTeamById(@PathVariable UUID deletedTeamUUID) {
        teamFacade.deleteTeamById(deletedTeamUUID);
        return ResponseEntity.status(HttpStatus.OK).body("Team with id " + deletedTeamUUID + " was successfully deleted.");
    }

    @PutMapping("/{teamUUID}/members/add")
    public ResponseEntity<String> addMembersToTeamById(@PathVariable UUID teamUUID,
                                                       @Valid @RequestBody AddDeleteMembersToTeamRequestDto addDeleteMembersToTeamRequestDto) {
        teamFacade.addMembersToTeamById(teamUUID, addDeleteMembersToTeamRequestDto.memberIds());
        return ResponseEntity.status(HttpStatus.OK).body("Participants have been successfully added to the team");
    }

    @PutMapping("/{teamUUID}/members/delete")
    public ResponseEntity<String> deleteMembersFromTeamById(@PathVariable UUID teamUUID,
                                                       @Valid @RequestBody AddDeleteMembersToTeamRequestDto addDeleteMembersToTeamRequestDto) {
        teamFacade.deleteMembersFromTeamById(teamUUID, addDeleteMembersToTeamRequestDto.memberIds());
        return ResponseEntity.status(HttpStatus.OK).body("Participants have been successfully removed from the team");
    }

    @PutMapping("/{teamUUID}/project/{projectUUID}/add")
    public ResponseEntity<String> addProjectToTeamById(@PathVariable UUID teamUUID,
                                                       @PathVariable UUID projectUUID) {
        teamFacade.addProjectToTeamById(teamUUID, projectUUID);
        return ResponseEntity.status(HttpStatus.OK).body("The project has been successfully added to the team");
    }

    @PutMapping("/{teamUUID}/project/delete")
    public ResponseEntity<String> deleteProjectFromTeamById(@PathVariable UUID teamUUID) {
        teamFacade.deleteProjectFromTeamById(teamUUID);
        return ResponseEntity.status(HttpStatus.OK).body("The project has been successfully removed from the team");
    }
}
