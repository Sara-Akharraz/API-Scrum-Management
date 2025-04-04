package com.apiscrum.apiscrum.Controller;

import com.apiscrum.apiscrum.DTO.SprintDTO;
import com.apiscrum.apiscrum.Entity.Sprint;
import com.apiscrum.apiscrum.Mapper.SprintMapper;
import com.apiscrum.apiscrum.Service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sprint")
public class SprintController {
    @Autowired
    private SprintService sprintService;
    @Autowired
    private SprintMapper sprintMapper;
    @PostMapping
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<SprintDTO> addSprint(@Validated @RequestBody SprintDTO sprintDTO){
        Sprint sprint=sprintMapper.toEntity(sprintDTO);
        Sprint newSprint =sprintService.addSprint(sprint);
        return new ResponseEntity<>(sprintMapper.toDTO(newSprint), HttpStatus.CREATED);
    }
    @GetMapping
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<List<SprintDTO>> getAllSprints(){
        List<SprintDTO> sprints=sprintService.getAllSprints().stream()
                .map(sprintMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sprints);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<SprintDTO> getSprintById(@PathVariable Long id){
        Sprint sprint =sprintService.getSprintById(id);
        return ResponseEntity.ok(sprintMapper.toDTO(sprint));
    }
    @GetMapping("/project/{id}")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<List<SprintDTO>> getAllSprintsByProject(@PathVariable Long id){
        List<SprintDTO> sprints=sprintService.getAllSprintsByProject(id).stream()
                .map(sprintMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sprints);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<SprintDTO> updateSprint(@PathVariable Long id,@RequestBody SprintDTO sprintDTO){
        Sprint sprint =sprintMapper.toEntity(sprintDTO);
        Sprint updatedSprint=sprintService.updateSprint(id,sprint);
        return ResponseEntity.ok(sprintMapper.toDTO(updatedSprint));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SCRUM_MANAGER')")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id){
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }


}
