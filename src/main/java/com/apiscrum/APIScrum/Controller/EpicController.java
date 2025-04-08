package com.apiscrum.apiscrum.Controller;

import com.apiscrum.apiscrum.DTO.EpicDto;
import com.apiscrum.apiscrum.DTO.ProductBackLogDto;
import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Service.EpicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/epic")
public class EpicController {

    @Autowired
    private EpicService epicService;

    @GetMapping
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> getEpics() {
        try {
            List<EpicDto> epics = epicService.getEpics();
            return new ResponseEntity<>(epics, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> getEpic(@PathVariable("id") Long epicId) {
        try{
            return ResponseEntity.ok(epicService.getEpic(epicId));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addUserStory/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> addUserStoryToEpic(@PathVariable("id") Long id, @Valid @RequestBody UserStoryDto userStoryDto) {
        try{
            return ResponseEntity.ok(epicService.addUserStoryToEpic(userStoryDto,id));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id_pb}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> addEpic(@Valid @RequestBody EpicDto epic, @PathVariable Long id_pb ) {
        try{
            return ResponseEntity.ok(epicService.addEpic(epic, id_pb));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<String> deleteEpic(@PathVariable Long id) {
        try {
            epicService.deleteEpic(id);
            return ResponseEntity.ok("Epic with ID "+id+" deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> updateEpic(@RequestBody EpicDto epic, @PathVariable("id") Long id) {
        try{
            return ResponseEntity.ok(epicService.updateEpic(epic,id));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getUserStories/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> getUserEpicStories(@PathVariable Long id){
        try{
        return ResponseEntity.ok(epicService.getUserEpicStories(id));
    }catch(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
