package com.apiscrum.APIScrum.Controller;

import com.apiscrum.APIScrum.DTO.SprintBackLogDTO;
import com.apiscrum.APIScrum.DTO.TaskDTO;
import com.apiscrum.APIScrum.Entity.SprintBackLog;
import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Mapper.SprintBackLogMapper;
import com.apiscrum.APIScrum.Mapper.TaskMapper;
import com.apiscrum.APIScrum.Service.SprintBackLogService;
import com.apiscrum.APIScrum.Service.TaskService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sprintBackLog")
public class SprintBackLogController {
    @Autowired
    private SprintBackLogService sprintBackLogService;
    @Autowired
    private SprintBackLogMapper sprintBackLogMapper;
    @Autowired
    private TaskMapper taskMapper;


    @PostMapping
    public ResponseEntity<SprintBackLogDTO> addSprintBackLog(@Validated @RequestBody SprintBackLogDTO sprintBackLogDTO){
        SprintBackLog sprintBackLog=sprintBackLogMapper.toEntity(sprintBackLogDTO);
        SprintBackLog newSprintBackLog=sprintBackLogService.addSprintBackLog(sprintBackLog);
        return new ResponseEntity<>(sprintBackLogMapper.toDTO(newSprintBackLog), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<UserStory>> getUserStoriesAssociated(@PathVariable Long id){
       List<UserStory> userStories= sprintBackLogService.getUserStoriesAssociated(id);
        return userStories.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(userStories);
    }
    @GetMapping("/{id}/todo")
    public ResponseEntity<List<TaskDTO>> getToDoTasks(@PathVariable Long id){
        List<TaskDTO> tasks= sprintBackLogService.getToDoTasks(id).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return tasks.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}/in-progress")
    public ResponseEntity<List<TaskDTO>> getInProgressTasks(@PathVariable Long id){
        List<TaskDTO> tasks= sprintBackLogService.getInProgressTasks(id).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return tasks.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}/blocked")
    public ResponseEntity<List<TaskDTO>> getBlockedTasks(@PathVariable Long id){
        List<TaskDTO> tasks= sprintBackLogService.getBlockedTasks(id).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return tasks.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}/done")
    public ResponseEntity<List<TaskDTO>> getDoneTasks(@PathVariable Long id){
        List<TaskDTO> tasks= sprintBackLogService.getDoneTasks(id).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return tasks.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(tasks);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SprintBackLogDTO>updateSprintBackLog(@PathVariable Long id,@RequestBody SprintBackLogDTO sprintBackLogDTO){
        SprintBackLog sprintBackLog=sprintBackLogMapper.toEntity(sprintBackLogDTO);
        SprintBackLog updatedSprintBacklog=sprintBackLogService.updateSprintBackLog(id,sprintBackLog);
        return ResponseEntity.ok(sprintBackLogMapper.toDTO(updatedSprintBacklog));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprintBackLog(@PathVariable Long id){
        sprintBackLogService.deleteSprintBackLog(id);
        return ResponseEntity.noContent().build();
    }

}
