package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.Project;
import com.apiscrum.apiscrum.Repository.ProjectRepository;
import com.apiscrum.apiscrum.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Override
    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public Project getProject(Long id) {
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        return project;
    }

    @Override
    public Optional<Project> getProjectByProductBackLog(Long id) {
        return projectRepository.getProjectByProductBackLog(id);
    }
}
