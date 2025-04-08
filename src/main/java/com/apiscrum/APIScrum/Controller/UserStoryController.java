package com.apiscrum.apiscrum.Controller;


import com.apiscrum.apiscrum.DTO.UserStoryDto;
import com.apiscrum.apiscrum.Entity.UserStory;
import com.apiscrum.apiscrum.Mapper.UserStoryMapper;
import com.apiscrum.apiscrum.Service.UserStoryService;
import com.apiscrum.apiscrum.enums.UserStoryProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/userStory")
public class UserStoryController {

    @Autowired
    private UserStoryService userStoryService;
    @Autowired
    private UserStoryMapper userStoryMapper;

    public UserStoryController(UserStoryService userStoryService, UserStoryMapper userStoryMapper) {
        this.userStoryService = userStoryService;
        this.userStoryMapper = userStoryMapper;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
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
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<?> getUserStory(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(userStoryService.getUserStory(id));
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping({"/{id}/progress"})
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<UserStoryDto> updateUserStoryProgress(@PathVariable Long id,@RequestBody UserStoryProgress updatedprogress){
        UserStory userStory=userStoryService.updateUserStoryProgress(id,updatedprogress);
        return ResponseEntity.ok(userStoryMapper.mapToUserStoryDTO(userStory));

    }
//    @PostMapping()
//@PreAuthorize("hasRole('PRODUCT_OWNER')")
//    public ResponseEntity<UserStoryDto> addUserStory(@RequestBody UserStoryDto userStoryDto) {
//        UserStory userStory = userStoryMapper.mapToUserStory(userStoryDto);
//        UserStory newUserStory = userStoryService.addUserStory(userStory);
//        return new ResponseEntity<>(userStoryMapper.mapToUserStoryDTO(newUserStory), HttpStatus.CREATED);
//    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<UserStoryDto> updateUserStory(@PathVariable Long id, @RequestBody UserStoryDto userStoryDto) {
        UserStory userStory = userStoryMapper.mapToUserStory(userStoryDto);
        UserStory updatedUserStory = userStoryService.updateUserStory(id, userStory);
        return ResponseEntity.ok(userStoryMapper.mapToUserStoryDTO(updatedUserStory));
    }
//    @GetMapping("/userStories")
//    @PreAuthorize("hasRole('PRODUCT_OWNER')")
//    public ResponseEntity<List<UserStoryDto>> getAllUserStories() {
//        List<UserStoryDto> userStoryDtos = userStoryService.getAllUserStories().stream()
//                .map(UserStoryMapper::mapToUserStoryDTO)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(userStoryDtos);
//    }
}
