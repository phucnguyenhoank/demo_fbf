package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.dto.FbfUserDto;
import com.example_fbf.demo_fbf.entity.Cart;
import com.example_fbf.demo_fbf.entity.FbfUser;
import com.example_fbf.demo_fbf.mapper.FbfUserMapper;
import com.example_fbf.demo_fbf.repository.CartRepository;
import com.example_fbf.demo_fbf.repository.FbfUserRepository;
import com.example_fbf.demo_fbf.service.FbfUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FbfUserServiceImpl implements FbfUserService {

    private final FbfUserRepository fbfUserRepository;
    private final CartRepository cartRepository;
    private final FbfUserMapper fbfUserMapper;

//    @Override
//    public FbfUserDto registerUser(FbfUserDto userDto) {
//        // You can add username/email existence check here if needed
//
//        FbfUser fbfUser = fbfUserMapper.toEntity(userDto);
//        FbfUser savedfbfUser = fbfUserRepository.save(fbfUser);
//        return fbfUserMapper.toDto(savedfbfUser);
//    }

    @Override
    public FbfUser registerFbfUser(FbfUser fbfUser) {
        if (fbfUserRepository.findByUsername(fbfUser.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        if (fbfUserRepository.findByEmail(fbfUser.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        FbfUser savedUser = fbfUserRepository.save(fbfUser);
        Cart cart = new Cart();
        cart.setFbfUser(savedUser);
        cartRepository.save(cart);
        savedUser.setCart(cart);
        return savedUser;
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        FbfUser fbfUser = fbfUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

        fbfUser.setPassword(newPassword);
        fbfUserRepository.save(fbfUser);
    }

    @Override
    public FbfUser findByUsername(String username) {
        return fbfUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    @Override
    public FbfUser findByEmail(String email) {
        return fbfUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }
}

