package com.simt.sync.dto;

import com.simt.sync.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}