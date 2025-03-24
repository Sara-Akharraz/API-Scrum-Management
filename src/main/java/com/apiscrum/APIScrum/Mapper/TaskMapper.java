package com.apiscrum.APIScrum.Mapper;

import com.apiscrum.APIScrum.DTO.TaskDTO;
import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.Entity.User;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Repository.UserRepository;
import com.apiscrum.APIScrum.Repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskMapper {

    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private UserRepository userRepository;
    public  TaskDTO toDTO(Task task){
        return new TaskDTO(
                task.getId(),
                task.getTask_title(),
                task.getDescription(),
                task.getTag(),
                task.getProgress(),
                task.getAssociatedUserStory().getId(),
                task.getTaskOwner().getId()
        );
    }

    public  Task toEntity(TaskDTO taskDTO){
        User taskOwner=userRepository.findById(taskDTO.getTaskOwner()).orElseThrow(()->new RuntimeException("User not found"));
        UserStory userStory=userStoryRepository.findById(taskDTO.getAssociatedUserStoryId()).orElseThrow(()-> new RuntimeException("User Story not found"));
       return new Task(
               taskDTO.getId(),
               taskDTO.getTaskTitle(),
               taskDTO.getDescription(),
               taskDTO.getProgress(),
               taskOwner,
               taskDTO.getTag(),
               userStory
       );
    }
}
