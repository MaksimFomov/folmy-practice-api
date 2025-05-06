package com.folmy.folmypractice.service.impl;

import com.folmy.folmypractice.exception.notfound.ProjectNotFoundException;
import com.folmy.folmypractice.model.Project;
import com.folmy.folmypractice.repository.ProjectRepository;
import com.folmy.folmypractice.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(UUID projectUUID) {
        return projectRepository.findById(projectUUID)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectUUID));
    }

    @Override
    public Project createProject(Project newProject) {
        return projectRepository.save(newProject);
    }

    @Override
    public Project updateProjectById(UUID projectUUID, Project updatedProject) {
        Project existingProject = getProjectById(projectUUID);

        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setBriefDescription(updatedProject.getBriefDescription());
        existingProject.setTermsOfReference(updatedProject.getTermsOfReference());
        existingProject.setUpdatedAt(LocalDateTime.now());

        return projectRepository.save(existingProject);
    }

    @Override
    public void deleteProjectById(UUID projectUUID) {
        projectRepository.delete(getProjectById(projectUUID));
    }
}
