package com.apiscrum.APIScrum.Controller;


import com.apiscrum.APIScrum.Entity.Project;
import com.apiscrum.APIScrum.Service.ProjectService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;

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
