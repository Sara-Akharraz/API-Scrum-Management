package com.apiscrum.apiscrum.Mapper;

import com.apiscrum.apiscrum.DTO.ProjectDto;
import com.apiscrum.apiscrum.Entity.Project;

public class ProjectMapper {
    public static ProjectDto mapToProjectDTO(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .productBackLog(project.getProductBackLog())
                .build();
    }

    public static Project mapToProject(ProjectDto projectDto) {
        return Project.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .productBackLog(projectDto.getProductBackLog())
                .build();
    }
}
