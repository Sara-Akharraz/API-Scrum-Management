package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.Entity.Project;

import java.util.Optional;

public interface ProjectService {
    public void addProject(Project project);
    public Project getProject(Long id);
    public Optional<Project> getProjectByProductBackLog(Long id);

}
