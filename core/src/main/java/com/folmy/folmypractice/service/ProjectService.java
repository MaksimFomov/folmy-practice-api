package com.folmy.folmypractice.service;

import com.folmy.folmypractice.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    List<Project> getAllProjects();

    Project getProjectById(UUID projectUUID);

    Project createProject(Project newProject);

    Project updateProjectById(UUID projectUUID, Project updatedProject);

    void deleteProjectById(UUID projectUUID);
}
