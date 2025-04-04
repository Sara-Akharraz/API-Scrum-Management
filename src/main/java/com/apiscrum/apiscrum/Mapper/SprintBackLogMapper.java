package com.apiscrum.apiscrum.Mapper;

import com.apiscrum.apiscrum.DTO.SprintBackLogDTO;
import com.apiscrum.apiscrum.Entity.Sprint;
import com.apiscrum.apiscrum.Entity.SprintBackLog;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Repository.SprintRepository;
import com.apiscrum.apiscrum.Repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
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
