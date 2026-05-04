package com.simt.sync.controller;

import com.simt.sync.dto.LoginRequest;
import com.simt.sync.dto.LoginResponse;
import com.simt.sync.dto.RegisterRequest;
import com.simt.sync.entity.User;
import com.simt.sync.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        return "Hello " + email;
    }
}