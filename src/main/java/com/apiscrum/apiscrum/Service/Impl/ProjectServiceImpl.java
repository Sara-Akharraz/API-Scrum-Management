package com.apiscrum.APIScrum.Service.Impl;
import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Mapper.*;
import com.apiscrum.APIScrum.DTO.ProjectDto;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.Project;
import com.apiscrum.APIScrum.Mapper.ProjectMapper;
import com.apiscrum.APIScrum.Repository.ProductBackLogRepository;
import com.apiscrum.APIScrum.Repository.ProjectRepository;
import com.apiscrum.APIScrum.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProductBackLogRepository productBackLogRepository;
    @Override
    public ProjectDto addProject(ProjectDto projectDto) {
        Project project = ProjectMapper.mapToProject(projectDto);
        return ProjectMapper.mapToProjectDTO(projectRepository.save(project));
    }

    public ProjectDto getProject(Long id) {
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        return ProjectMapper.mapToProjectDTO(project);
    }

    @Override
    public List<ProjectDto> getProjects(){
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(ProjectMapper::mapToProjectDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<ProjectDto> getProjectByProductBackLog(Long id) {
        ProductBackLog pb = productBackLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Backlog not found with id: " + id));
        return Optional.of(ProjectMapper.mapToProjectDTO(pb.getProject()));
    }

    @Override
    public void deleteProject(Long id) {
        if(projectRepository.existsById(id))
            projectRepository.deleteById(id);
        else
            throw new RuntimeException("Project not found with id: " + id);
    }

}
