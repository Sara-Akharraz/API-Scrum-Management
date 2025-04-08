package com.apiscrum.apiscrum.Controller;

import com.apiscrum.apiscrum.Entity.User;
import com.apiscrum.apiscrum.Service.UserService;
import com.apiscrum.apiscrum.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            userService.register(user);
            return new ResponseEntity<>("User registered successfully with ID: " + user.getId(), HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>("Error registering user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            userService.updateUser(id, user);
            return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User  successfully deleted!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_role/{id}")
    public ResponseEntity<String> updateUserRole(@PathVariable Long id, @RequestBody Role role) {
        try {
            userService.updateUserRole(id, role);
            return new ResponseEntity<>("User role updated successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating user role: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
