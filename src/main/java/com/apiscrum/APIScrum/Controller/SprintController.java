package com.apiscrum.APIScrum.Controller;

import com.apiscrum.APIScrum.DTO.SprintDTO;
import com.apiscrum.APIScrum.Entity.Sprint;
import com.apiscrum.APIScrum.Mapper.SprintMapper;
import com.apiscrum.APIScrum.Service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SprintDTO> addSprint(@Validated @RequestBody SprintDTO sprintDTO){
        Sprint sprint=sprintMapper.toEntity(sprintDTO);
        Sprint newSprint =sprintService.addSprint(sprint);
        return new ResponseEntity<>(sprintMapper.toDTO(newSprint), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<SprintDTO>> getAllSprints(){
        List<SprintDTO> sprints=sprintService.getAllSprints().stream()
                .map(sprintMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sprints);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SprintDTO> getSprintById(@PathVariable Long id){
        Sprint sprint =sprintService.getSprintById(id);
        return ResponseEntity.ok(sprintMapper.toDTO(sprint));
    }
    @GetMapping
    public ResponseEntity<List<SprintDTO>> getAllSprintsByProject(@PathVariable Long id){
        List<SprintDTO> sprints=sprintService.getAllSprintsByProject(id).stream()
                .map(sprintMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sprints);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SprintDTO> updateSprint(@PathVariable Long id,@RequestBody SprintDTO sprintDTO){
        Sprint sprint =sprintMapper.toEntity(sprintDTO);
        Sprint updatedSprint=sprintService.updateSprint(id,sprint);
        return ResponseEntity.ok(sprintMapper.toDTO(updatedSprint));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id){
        sprintService.deleteSprint(id);
        return ResponseEntity.noContent().build();
    }


}
