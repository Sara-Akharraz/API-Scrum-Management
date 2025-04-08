package com.apiscrum.apiscrum.Service.Impl;
import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Mapper.*;
import com.apiscrum.apiscrum.DTO.ProjectDto;
import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.Project;
import com.apiscrum.apiscrum.Mapper.ProjectMapper;
import com.apiscrum.apiscrum.Repository.ProductBackLogRepository;
import com.apiscrum.apiscrum.Repository.ProjectRepository;
import com.apiscrum.apiscrum.Service.ProjectService;
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

    @Override
    public ProjectDto updateProject(ProjectDto projectDto, Long id) {
        Optional<Project> p = Optional.of(projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id)));
        p.get().setName(projectDto.getName());
        p.get().setDescription(projectDto.getDescription());
        projectRepository.save(p.get());
        return ProjectMapper.mapToProjectDTO(projectRepository.save(p.get()));
    }
}
