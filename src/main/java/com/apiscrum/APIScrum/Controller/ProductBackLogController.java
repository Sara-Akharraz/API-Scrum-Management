package com.apiscrum.APIScrum.Controller;


import com.apiscrum.APIScrum.Entity.Epic;
import com.apiscrum.APIScrum.Entity.ProductBackLog;
import com.apiscrum.APIScrum.Entity.Project;
import com.apiscrum.APIScrum.Entity.UserStory;
import com.apiscrum.APIScrum.Service.ProductBackLogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/productBacklog")
public class ProductBackLogController {
    private final ProductBackLogService productBackLogService;

    @GetMapping
    public List<ProductBackLog> getAllProductBackLog() {
        return productBackLogService.getAllProductBackLog();
    }
    @PostMapping
    public void addProductBackLog(@RequestBody ProductBackLog pb) {
        productBackLogService.addProductBackLog(pb);
    }
    @DeleteMapping("/{id}")
    public void deleteProductBackLog(@PathVariable Long id) {
        productBackLogService.deleteProductBackLog(id);
    }

}
