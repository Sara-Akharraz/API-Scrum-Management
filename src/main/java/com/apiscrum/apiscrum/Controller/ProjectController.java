package com.apiscrum.apiscrum.Controller;

import com.apiscrum.apiscrum.DTO.ProjectDto;
import com.apiscrum.apiscrum.Entity.Project;
import com.apiscrum.apiscrum.Service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectDto project) {
        try {
            ProjectDto createdProject = projectService.addProject(project);
            return new ResponseEntity<>("Project registered successfully with ID: " + createdProject.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDto project) {
        try{
           return ResponseEntity.ok(projectService.updateProject(project, id));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> getProject(@PathVariable("id") Long id) {
        try {
            ProjectDto project = projectService.getProject(id);
            return new ResponseEntity<>(project, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}/")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> getProjectByProductBackLog(@PathVariable("id") Long id){
        try {
            return getProjectByProductBackLog(id);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
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
