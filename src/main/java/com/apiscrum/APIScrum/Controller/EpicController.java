package com.apiscrum.APIScrum.Controller;

import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Service.EpicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/epic")
public class EpicController {
    private final EpicService epicService;

    @GetMapping
    public List<Epic> getEpics() {
        return epicService.getEpics();
    }
    @PostMapping
    public void addEpic(@RequestBody Epic epic) {
        epicService.addEpic(epic);
    }
    @DeleteMapping("/{id}")
    public void deleteEpic(@PathVariable Long id) {
        epicService.deleteEpic(id);
    }
    @GetMapping("/epicUserStories/{id}")
    public List<UserStory> getUserEpicStories(@PathVariable Long id){
        return epicService.getUserEpicStories(id);
    }
}
