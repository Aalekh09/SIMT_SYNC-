package com.simt.sync.dto;

import com.simt.sync.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private String email;
    private Role role;
    private String token;   // NEW
}