package com.apiscrum.apiscrum.Controller;

import com.apiscrum.apiscrum.DTO.TaskDTO;
import com.apiscrum.apiscrum.Entity.Task;
import com.apiscrum.apiscrum.Mapper.TaskMapper;
import com.apiscrum.apiscrum.Service.TaskService;
import com.apiscrum.apiscrum.enums.TaskProgress;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
   private final TaskService taskService;
   private final TaskMapper taskMapper;
   public TaskController(TaskService taskService,TaskMapper taskMapper){
       this.taskMapper=taskMapper;
       this.taskService=taskService;
   }

    @PostMapping
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO){
        Task task=taskMapper.toEntity(taskDTO);
        Task newTask =taskService.addTask(task);
        return new ResponseEntity<>(taskMapper.toDTO(newTask), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
        Task task=taskService.getTaskById(id);
        return ResponseEntity.ok(taskMapper.toDTO(task));
    }
    @GetMapping
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> taskDTOs = taskService.getAllTasks().stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOs);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id,@RequestBody TaskDTO taskDTO){
        Task task=taskMapper.toEntity(taskDTO);
        Task updatedTask=taskService.updateTask(id,task);
        return ResponseEntity.ok(taskMapper.toDTO(updatedTask));
    }
    @PutMapping("/{id}/progress")
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<TaskDTO> updateTaskProgress(@PathVariable Long id,@RequestBody TaskProgress progress){
        Task task=taskService.updateTaskProgress(id,progress);
        return ResponseEntity.ok(taskMapper.toDTO(task));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
