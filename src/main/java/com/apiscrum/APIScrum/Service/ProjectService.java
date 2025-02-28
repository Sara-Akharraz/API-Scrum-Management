package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.Entity.Project;

import java.util.Optional;

public interface ProjectService {
    public void addProject(Project project);
    public Optional<Project> getProject(Long id);
    public Optional<Project> getProjectByProductBackLog(Long id);

}
