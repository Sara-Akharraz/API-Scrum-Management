package com.apiscrum.apiscrum.Service;

import com.apiscrum.apiscrum.Entity.User;
import com.apiscrum.apiscrum.Security.DTO.AuthResponse;
import com.apiscrum.apiscrum.Security.DTO.LoginRequest;
import com.apiscrum.apiscrum.enums.Role;

public interface UserService {
    AuthResponse login(LoginRequest request);
    public User register(User user);
    public void deleteUser(Long id);
    public void updateUser(Long id, User updatedUser);
    public void updateUserRole(Long id, Role role);
}
