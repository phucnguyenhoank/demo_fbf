package com.example_fbf.demo_fbf.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private String otp;
}
