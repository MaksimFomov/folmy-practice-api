package com.folmy.folmypractice.controller;

import com.folmy.folmypractice.dto.ProjectDetailResponseDto;
import com.folmy.folmypractice.dto.ProjectRequestDto;
import com.folmy.folmypractice.dto.ProjectSummaryResponseDto;
import com.folmy.folmypractice.facade.ProjectFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectFacade projectFacade;

    public ProjectController(ProjectFacade projectFacade) {
        this.projectFacade = projectFacade;
    }

    @GetMapping
    public ResponseEntity<List<ProjectSummaryResponseDto>> getAllProjects() {
        List<ProjectSummaryResponseDto> projects = projectFacade.getAllProjects();
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @GetMapping("/{projectUUID}")
    public ResponseEntity<ProjectDetailResponseDto> getProjectById(@PathVariable UUID projectUUID) {
        ProjectDetailResponseDto project = projectFacade.getProjectById(projectUUID);
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @PostMapping
    public ResponseEntity<ProjectSummaryResponseDto> createProject(@Valid @RequestBody ProjectRequestDto newProjectRequestDto) {
        ProjectSummaryResponseDto newProject = projectFacade.createProject(newProjectRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProject);
    }

    @PutMapping("/{updatedProjectUUID}")
    public ResponseEntity<ProjectSummaryResponseDto> updateProjectById(@PathVariable UUID updatedProjectUUID,
                                                                   @Valid @RequestBody ProjectRequestDto updatedProjectRequestDto) {
        ProjectSummaryResponseDto updatedProject = projectFacade.updateProjectById(updatedProjectUUID, updatedProjectRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProject);
    }

    @PutMapping("/{deletedProjectUUID}")
    public ResponseEntity<String> deleteProjectById(@PathVariable UUID deletedProjectUUID) {
        projectFacade.deleteProjectById(deletedProjectUUID);
        return ResponseEntity.status(HttpStatus.OK).body("Project with id " + deletedProjectUUID + " was successfully deleted.");
    }
}
