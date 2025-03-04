package com.apiscrum.APIScrum.Controller;

import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Service.UserStoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/userStory")
public class UserStoryController {
    private final UserStoryService userStoryService;

    @PostMapping
    public void createUserStory(@RequestBody UserStory userStory) {
        userStoryService.createUserStory(userStory);
    }
    @DeleteMapping("/{id}")
    public void deleteUserStory( @PathVariable Long id){
        userStoryService.deleteUserStory(id);
    }
    @GetMapping
    public UserStory getUserStory(Long id){
        return userStoryService.getUserStory(id);
    }
}
