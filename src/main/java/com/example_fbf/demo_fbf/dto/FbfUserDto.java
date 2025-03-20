package com.example_fbf.demo_fbf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FbfUserDto {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
}
