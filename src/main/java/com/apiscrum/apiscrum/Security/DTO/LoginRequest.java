package com.apiscrum.apiscrum.Security.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
