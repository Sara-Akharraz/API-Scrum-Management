package com.apiscrum.apiscrum.Mapper;

import com.apiscrum.apiscrum.DTO.SprintDTO;
import com.apiscrum.apiscrum.Entity.*;
import com.apiscrum.apiscrum.Repository.ProjectRepository;
import com.apiscrum.apiscrum.Repository.SprintBackLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {
    @Autowired
    private SprintBackLogRepository sprintBackLogRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public SprintDTO toDTO(Sprint sprint){
        Long sprintBackLogId = (sprint.getSprintBackLog() != null)
                ? sprint.getSprintBackLog().getId()
                : null;
        return new SprintDTO(
                sprint.getId(),
                sprint.getSprint_title(),
                sprint.getDuration(),
                sprint.getStart_date(),
                sprint.getEnd_date(),
                sprint.getDescription(),
                sprintBackLogId,
                sprint.getAssociatedProject().getId()
        );
    }
    public Sprint toEntity(SprintDTO sprintDTO){
        SprintBackLog sprintBackLog = null;

        if (sprintDTO.getSprintBackLogId() != null && sprintDTO.getSprintBackLogId() > 0) {
            sprintBackLog = sprintBackLogRepository.findById(sprintDTO.getSprintBackLogId())
                    .orElseThrow(() -> new RuntimeException("SprintBackLog not found"));
        }
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
