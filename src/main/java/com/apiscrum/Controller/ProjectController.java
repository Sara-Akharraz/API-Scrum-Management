package com.apiscrum.APIScrum.Controller;

import com.apiscrum.APIScrum.DTO.ProjectDto;
import com.apiscrum.APIScrum.Entity.Project;
import com.apiscrum.APIScrum.Service.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectDto project) {
        try {
            ProjectDto createdProject = projectService.addProject(project);
            return new ResponseEntity<>("Project registered successfully with ID: " + createdProject.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDto project) {
        try{
           return ResponseEntity.ok(projectService.updateProject(project, id));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable("id") Long id) {
        try {
            ProjectDto project = projectService.getProject(id);
            return new ResponseEntity<>(project, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}/")
    public ResponseEntity<?> getProjectByProductBackLog(@PathVariable("id") Long id){
        try {
            return getProjectByProductBackLog(id);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") Long id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok("Project deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + ex.getMessage());
        }
    }
}