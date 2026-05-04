package com.simt.sync.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {



        @GetMapping("/test-open")
        public String test() {
            return "Backend Working 🚀";
        }
}
