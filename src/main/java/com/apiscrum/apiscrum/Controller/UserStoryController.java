package com.apiscrum.APIScrum.Controller;

import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Service.UserStoryService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/userStory")
public class UserStoryController {

    @Autowired
    private UserStoryService userStoryService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserStory(@PathVariable("id") Long id){
        try {
            userStoryService.deleteUserStory(id);
            return ResponseEntity.ok("User Story with id "+id+" deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserStory(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(userStoryService.getUserStory(id));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
