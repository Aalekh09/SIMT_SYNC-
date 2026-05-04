package com.simt.sync.service;

import com.simt.sync.dto.LoginRequest;
import com.simt.sync.dto.LoginResponse;
import com.simt.sync.dto.RegisterRequest;
import com.simt.sync.entity.User;
import com.simt.sync.repository.UserRepository;
import com.simt.sync.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // 🔐 ENCRYPT PASSWORD
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    @Autowired
    private JwtUtil jwtUtil;
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 🔥 generate token
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponse(
                "Login successful",
                user.getEmail(),
                user.getRole(),
                token
        );
    }
}