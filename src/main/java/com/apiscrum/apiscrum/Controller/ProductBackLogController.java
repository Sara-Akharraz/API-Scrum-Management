package com.apiscrum.APIScrum.Controller;


import com.apiscrum.APIScrum.DTO.ProductBackLogDto;
import com.apiscrum.APIScrum.DTO.UserStoryDto;
import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.Project;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Service.ProductBackLogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/productBacklog")
public class ProductBackLogController {

    @Autowired
    private ProductBackLogService productBackLogService;

    @PostMapping("/addUserStory/{id_pb}")
    public ResponseEntity<?> addUserStoryToPB(@Valid @RequestBody UserStoryDto userStory, @PathVariable("id_pb") Long id_pb) {
        try{
            return ResponseEntity.ok(productBackLogService.addUserStoryToPB(userStory,id_pb));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProductBackLog() {
        try{
            List<ProductBackLogDto> productBackLogs = productBackLogService.getAllProductBackLog();
            return new ResponseEntity<>(productBackLogs, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUserStories/{id}")
    public ResponseEntity<?> getUserStories(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(productBackLogService.getUserStoriesByProductBackLog(id));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/{id_project}")
    public ResponseEntity<?> addProductBackLog(@Valid  @RequestBody String title, @PathVariable("id_project") Long id_project) {
       try {
            return ResponseEntity.ok(productBackLogService.addProductBackLog(title, id_project));
        }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductBackLog(@PathVariable Long id) {
        try {
            productBackLogService.deleteProductBackLog(id);
            return ResponseEntity.ok("Product BackLog deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + ex.getMessage());
        }
    }
}
