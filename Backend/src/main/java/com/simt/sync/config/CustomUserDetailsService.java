package com.simt.sync.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class CustomUserDetailsService {

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> null; // 🔥 disables default user
    }
}