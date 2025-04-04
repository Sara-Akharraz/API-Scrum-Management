package com.apiscrum.apiscrum.Controller;

import com.apiscrum.apiscrum.DTO.SprintBackLogDTO;
import com.apiscrum.apiscrum.DTO.TaskDTO;
import com.apiscrum.apiscrum.DTO.TestAcceptanceDTO;
import com.apiscrum.apiscrum.Entity.SprintBackLog;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Mapper.SprintBackLogMapper;
import com.apiscrum.apiscrum.Mapper.TaskMapper;
import com.apiscrum.apiscrum.Mapper.TestAcceptanceMapper;
import com.apiscrum.apiscrum.Service.SprintBackLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private TestAcceptanceMapper testAcceptanceMapper;


    @PostMapping
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<SprintBackLogDTO> addSprintBackLog(@Validated @RequestBody SprintBackLogDTO sprintBackLogDTO){
        SprintBackLog sprintBackLog=sprintBackLogMapper.toEntity(sprintBackLogDTO);
        SprintBackLog newSprintBackLog=sprintBackLogService.addSprintBackLog(sprintBackLog);
        return new ResponseEntity<>(sprintBackLogMapper.toDTO(newSprintBackLog), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<SprintBackLogDTO> getSprintBackLogById(@PathVariable Long id) {
        try {
            SprintBackLog sprintBackLog = sprintBackLogService.getSprintBackLogById(id);
            SprintBackLogDTO dto = sprintBackLogMapper.toDTO(sprintBackLog);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/{id}/ListUserStory")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<List<UserStory>> getUserStoriesAssociated(@PathVariable Long id){
       List<UserStory> userStories= sprintBackLogService.getUserStoriesAssociated(id);
        return userStories.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(userStories);
    }
    @GetMapping("/{id}/todo")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<List<TaskDTO>> getToDoTasks(@PathVariable Long id){
        List<TaskDTO> tasks= sprintBackLogService.getToDoTasks(id).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return tasks.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}/in-progress")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<List<TaskDTO>> getInProgressTasks(@PathVariable Long id){
        List<TaskDTO> tasks= sprintBackLogService.getInProgressTasks(id).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return tasks.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}/blocked")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<List<TaskDTO>> getBlockedTasks(@PathVariable Long id){
        List<TaskDTO> tasks= sprintBackLogService.getBlockedTasks(id).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return tasks.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}/done")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<List<TaskDTO>> getDoneTasks(@PathVariable Long id){
        List<TaskDTO> tasks= sprintBackLogService.getDoneTasks(id).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return tasks.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(tasks);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<SprintBackLogDTO>updateSprintBackLog(@PathVariable Long id,@RequestBody SprintBackLogDTO sprintBackLogDTO){
        SprintBackLog sprintBackLog=sprintBackLogMapper.toEntity(sprintBackLogDTO);
        SprintBackLog updatedSprintBacklog=sprintBackLogService.updateSprintBackLog(id,sprintBackLog);
        return ResponseEntity.ok(sprintBackLogMapper.toDTO(updatedSprintBacklog));

    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<Void> deleteSprintBackLog(@PathVariable Long id){
        sprintBackLogService.deleteSprintBackLog(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/tests/passed")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<List<TestAcceptanceDTO>> getPassedTests(@PathVariable Long id) {
        List<TestAcceptanceDTO> tests = sprintBackLogService.getPassedTests(id)
                .stream()
                .map(testAcceptanceMapper::toDTO)
                .collect(Collectors.toList());
        return tests.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(tests);
    }

    @GetMapping("/{id}/tests/pending")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<List<TestAcceptanceDTO>> getPendingTests(@PathVariable Long id) {
        List<TestAcceptanceDTO> tests = sprintBackLogService.getPendingTests(id)
                .stream()
                .map(testAcceptanceMapper::toDTO)
                .collect(Collectors.toList());
        return tests.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(tests);
    }

    @GetMapping("/{id}/tests/failed")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<List<TestAcceptanceDTO>> getFailedTests(@PathVariable Long id) {
        List<TestAcceptanceDTO> tests = sprintBackLogService.getFailedTests(id)
                .stream()
                .map(testAcceptanceMapper::toDTO)
                .collect(Collectors.toList());
        return tests.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(tests);
    }

}
