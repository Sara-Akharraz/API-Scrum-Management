package com.apiscrum.APIScrum.Controller;

import com.apiscrum.APIScrum.DTO.TaskDTO;
import com.apiscrum.APIScrum.Entity.Task;
import com.apiscrum.APIScrum.Mapper.TaskMapper;
import com.apiscrum.APIScrum.Service.TaskService;
import com.apiscrum.APIScrum.enums.TaskProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO){
        Task task=taskMapper.toEntity(taskDTO);
        Task newTask =taskService.addTask(task);
        return new ResponseEntity<>(taskMapper.toDTO(newTask), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
        Task task=taskService.getTaskById(id);
        return ResponseEntity.ok(taskMapper.toDTO(task));
    }
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> taskDTOs = taskService.getAllTasks().stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOs);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id,@RequestBody TaskDTO taskDTO){
        Task task=taskMapper.toEntity(taskDTO);
        Task updatedTask=taskService.updateTask(id,task);
        return ResponseEntity.ok(taskMapper.toDTO(updatedTask));
    }
    @PutMapping("/{id}/progress")
    public ResponseEntity<TaskDTO> updateTaskProgress(@PathVariable Long id,@RequestBody TaskProgress progress){
        Task task=taskService.updateTaskProgress(id,progress);
        return ResponseEntity.ok(taskMapper.toDTO(task));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
