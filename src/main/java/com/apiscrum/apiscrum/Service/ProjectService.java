package com.apiscrum.APIScrum.Service;

import com.apiscrum.APIScrum.DTO.ProjectDto;
import com.apiscrum.APIScrum.Entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    public ProjectDto addProject(ProjectDto project);
    public List<ProjectDto> getProjects();
    public ProjectDto getProject(Long id);
    public Optional<ProjectDto> getProjectByProductBackLog(Long id);
    public void deleteProject(Long id);
}
