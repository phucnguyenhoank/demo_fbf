package com.example_fbf.demo_fbf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFbfUserRequest {
    private String phoneNumber;
    private String address;
}
