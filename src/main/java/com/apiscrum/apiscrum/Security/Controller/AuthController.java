package com.apiscrum.apiscrum.Security.Controller;

import com.apiscrum.apiscrum.Security.DTO.AuthResponse;
import com.apiscrum.apiscrum.Security.DTO.LoginRequest;
import com.apiscrum.apiscrum.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }
}
