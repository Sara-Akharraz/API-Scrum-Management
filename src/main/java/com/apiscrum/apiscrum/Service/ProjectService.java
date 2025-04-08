package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.DTO.ProjectDto;
import com.apiscrum.apiscrum.Entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    public ProjectDto addProject(ProjectDto project);
    public List<ProjectDto> getProjects();
    public ProjectDto getProject(Long id);
    public Optional<ProjectDto> getProjectByProductBackLog(Long id);
    public ProjectDto updateProject(ProjectDto project, Long id);
    public void deleteProject(Long id);
}
