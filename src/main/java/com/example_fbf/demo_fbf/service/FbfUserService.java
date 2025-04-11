package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.dto.FbfUserDto;
import com.example_fbf.demo_fbf.entity.Cart;
import com.example_fbf.demo_fbf.entity.FbfUser;
import com.example_fbf.demo_fbf.mapper.FbfUserMapper;
import com.example_fbf.demo_fbf.repository.CartRepository;
import com.example_fbf.demo_fbf.repository.FbfUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
public interface FbfUserService {
    FbfUserDto registerUser(FbfUserDto userDto);
    FbfUser saveFbfUser(FbfUser fbfUser);
    void updatePassword(String email, String newPassword);
    FbfUser findByUsername(String username);
    FbfUser findByEmail(String email);
}

