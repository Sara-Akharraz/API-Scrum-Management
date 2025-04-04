package com.apiscrum.apiscrum.Controller;


import com.apiscrum.apiscrum.Entity.Project;
import com.apiscrum.apiscrum.Service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private  ProjectService projectService;

    @PostMapping
    public void createProject(@RequestBody Project project) {
        projectService.addProject(project);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id) {
        Project project = projectService.getProject(id);
        return ResponseEntity.ok(project);
    }

}
