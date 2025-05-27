package com.folmy.folmypractice.facade.impl;

import com.folmy.folmypractice.dto.ProjectDetailResponseDto;
import com.folmy.folmypractice.dto.ProjectRequestDto;
import com.folmy.folmypractice.dto.ProjectSummaryResponseDto;
import com.folmy.folmypractice.facade.ProjectFacade;
import com.folmy.folmypractice.mapper.ProjectMapper;
import com.folmy.folmypractice.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectFacadeImpl implements ProjectFacade {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectFacadeImpl(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<ProjectSummaryResponseDto> getAllProjects() {
        return projectMapper.toProjectSummaryResponseDto(
                projectService.getAllProjects()
        );
    }

    @Override
    public ProjectDetailResponseDto getProjectById(UUID projectUUID) {
        return projectMapper.toProjectDetailResponseDto(
                projectService.getProjectById(projectUUID)
        );
    }

    @Override
    public ProjectSummaryResponseDto createProject(ProjectRequestDto newProjectRequestDto) {
        return projectMapper.toProjectSummaryResponseDto(
                projectService.createProject(
                        projectMapper.toEntity(newProjectRequestDto)
                )
        );
    }

    @Override
    public ProjectSummaryResponseDto updateProjectById(UUID projectUUID, ProjectRequestDto updatedProjectRequestDto) {
        return projectMapper.toProjectSummaryResponseDto(
                projectService.updateProjectById(
                        projectUUID,
                        projectMapper.toEntity(updatedProjectRequestDto)
                )
        );
    }

    @Override
    public void deleteProjectById(UUID projectUUID) {
        projectService.deleteProjectById(projectUUID);
    }
}
