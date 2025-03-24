package com.apiscrum.APIScrum.Mapper;

import com.apiscrum.APIScrum.DTO.SprintBackLogDTO;
import com.apiscrum.APIScrum.Entity.Sprint;
import com.apiscrum.APIScrum.Entity.SprintBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Repository.SprintBackLogRepository;
import com.apiscrum.APIScrum.Repository.SprintRepository;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class SprintBackLogMapper {
    @Autowired
    private SprintRepository sprintRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;

    public SprintBackLogDTO toDTO(SprintBackLog sprintBackLog){
        List<Long> userStoryIds = sprintBackLog.getUserStoriesList().stream()
                .map(UserStory::getId)
                .collect(Collectors.toList());
        return new SprintBackLogDTO(
                sprintBackLog.getId(),
                userStoryIds,
                sprintBackLog.getAssociatedSprint().getId()
        );
    }
    public SprintBackLog toEntity(SprintBackLogDTO sprintBackLogDTO){
        Sprint sprint =sprintRepository.findById(sprintBackLogDTO.getAssociatedSprintId()).orElseThrow(()->new RuntimeException("Sprint not found"));
        List<UserStory> userStories=userStoryRepository.findAllById(sprintBackLogDTO.getUserStoriesList());
        return new SprintBackLog(
                sprintBackLogDTO.getId(),
                userStories,
                sprint
        );
    }
}
