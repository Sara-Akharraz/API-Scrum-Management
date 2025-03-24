package com.apiscrum.APIScrum.Mapper;

import com.apiscrum.APIScrum.DTO.SprintDTO;
import com.apiscrum.APIScrum.Entity.*;
import com.apiscrum.APIScrum.Repository.ProjectRepository;
import com.apiscrum.APIScrum.Repository.SprintBackLogRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SprintMapper {
    @Autowired
    private SprintBackLogRepository sprintBackLogRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public SprintDTO toDTO(Sprint sprint){
        return new SprintDTO(
                sprint.getId(),
                sprint.getSprint_title(),
                sprint.getDuration(),
                sprint.getStart_date(),
                sprint.getEnd_date(),
                sprint.getDescription(),
                sprint.getSprintBackLog().getId(),
                sprint.getAssociatedProject().getId()
        );
    }
    public Sprint toEntity(SprintDTO sprintDTO){
        SprintBackLog sprintBackLog=sprintBackLogRepository.findById(sprintDTO.getSprintBackLogId()).orElseThrow(()->new RuntimeException("SprintBackLog not found"));
        Project project=projectRepository.findById(sprintDTO.getAssociatedProjectId()).orElseThrow(()-> new RuntimeException("Project not found"));

        return new Sprint(
                null,// auto-generé
                sprintDTO.getSprint_title(),
                sprintDTO.getDuration(),
                sprintDTO.getStart_date(),
                sprintDTO.getEnd_date(),
                sprintDTO.getDescription(),
                sprintBackLog,
                project
        );
    }
}
