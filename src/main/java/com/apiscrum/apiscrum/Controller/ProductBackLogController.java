package com.apiscrum.apiscrum.Controller;


import com.apiscrum.apiscrum.DTO.ProductBackLogDto;
import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.Epic;
import com.apiscrum.apiscrum.Entity.ProductBackLog;
import com.apiscrum.apiscrum.Entity.Project;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Service.ProductBackLogService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/productBacklog")
public class ProductBackLogController {

    @Autowired
    private ProductBackLogService productBackLogService;

    @PostMapping("/addUserStory/{id_pb}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> addUserStoryToPB(@Valid @RequestBody UserStoryDto userStory, @PathVariable("id_pb") Long id_pb) {
        try{
            return ResponseEntity.ok(productBackLogService.addUserStoryToPB(userStory,id_pb));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> getAllProductBackLog() {
        try{
            List<ProductBackLogDto> productBackLogs = productBackLogService.getAllProductBackLog();
            return new ResponseEntity<>(productBackLogs, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUserStories/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> getUserStories(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(productBackLogService.getUserStoriesByProductBackLog(id));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/{id_project}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> addProductBackLog(@Valid  @RequestBody String title, @PathVariable("id_project") Long id_project) {
       try {
            return ResponseEntity.ok(productBackLogService.addProductBackLog(title, id_project));
        }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
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
