package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.entity.Cart;
import com.example_fbf.demo_fbf.repository.CartRepository;
import com.example_fbf.demo_fbf.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Long getCartIdByUserId(Long userId) {
        return cartRepository.findByFbfUserId(userId)
                .map(Cart::getId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for user ID: " + userId));
    }
}
