package com.folmy.folmypractice.facade;

import com.folmy.folmypractice.dto.ProjectDetailResponseDto;
import com.folmy.folmypractice.dto.ProjectRequestDto;
import com.folmy.folmypractice.dto.ProjectSummaryResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProjectFacade {
    List<ProjectSummaryResponseDto> getAllProjects();

    ProjectDetailResponseDto getProjectById(UUID projectUUID);

    ProjectSummaryResponseDto createProject(ProjectRequestDto newProjectRequestDto);

    ProjectSummaryResponseDto updateProjectById(UUID projectUUID, ProjectRequestDto updatedProjectRequestDto);

    void deleteProjectById(UUID projectUUID);
}
