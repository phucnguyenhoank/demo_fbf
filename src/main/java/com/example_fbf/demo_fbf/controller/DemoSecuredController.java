package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secured-request")
public class DemoSecuredController {

    @GetMapping
    public ResponseEntity<ApiResponse<String>> sayHello() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Access granted", "This is your secured data"));
    }
}
