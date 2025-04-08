package com.apiscrum.apiscrum.Service.Impl;

import com.apiscrum.apiscrum.Entity.User;
import com.apiscrum.apiscrum.Repository.UserRepository;
import com.apiscrum.apiscrum.Security.DTO.AuthResponse;
import com.apiscrum.apiscrum.Security.DTO.LoginRequest;
import com.apiscrum.apiscrum.Security.JWT.JwtUtil;
import com.apiscrum.apiscrum.Service.UserService;
import com.apiscrum.apiscrum.enums.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new RuntimeException("User not foud !"));
        if(!passwordEncoder.matches(request.getPassword(),user.getPasswd())){
            throw new RuntimeException("Incorrect password!");
        }
        String token =jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }
    @Transactional
    public User register(User user) {

        String encodedPassword = passwordEncoder.encode(user.getPasswd());
        user.setPasswd(encodedPassword);

        return userRepository.save(user);
    }
    public void updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(updatedUser.getEmail());
        user.setName(updatedUser.getName());
        user.setPasswd( new BCryptPasswordEncoder().encode(updatedUser.getPasswd()));
        user.setRole(updatedUser.getRole());

        userRepository.save(user);
    }

    public void updateUserRole(Long id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(role);

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }
}
