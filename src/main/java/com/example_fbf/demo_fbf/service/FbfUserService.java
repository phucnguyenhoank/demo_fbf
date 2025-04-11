package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.dto.FbfUserDto;
import com.example_fbf.demo_fbf.entity.FbfUser;
import com.example_fbf.demo_fbf.mapper.FbfUserMapper;
import com.example_fbf.demo_fbf.repository.FbfUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FbfUserService {

    @Autowired
    private FbfUserRepository fbfUserRepository;

    @Autowired
    private FbfUserMapper fbfUserMapper;

    public FbfUserDto registerUser(FbfUserDto userDto) {
        // Check if username or email already exists
        if (fbfUserRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken.");
        }
        if (fbfUserRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        // Convert DTO to entity
        FbfUser fbfUser = fbfUserMapper.toEntity(userDto);

        // Save user to the database
        FbfUser savedfbfUser = fbfUserRepository.save(fbfUser);

        // Convert back to DTO and return
        return fbfUserMapper.toDto(savedfbfUser);
    }

    public void updatePassword(String email, String newPassword) {
        FbfUser fbfUser = fbfUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        fbfUser.setPassword(newPassword);

        fbfUserRepository.save(fbfUser);
    }

    public FbfUser findByEmail(String email) {
        return fbfUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }
}
