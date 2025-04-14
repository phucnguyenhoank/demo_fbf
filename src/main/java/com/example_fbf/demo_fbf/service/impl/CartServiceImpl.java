package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.dto.CartDto;
import com.example_fbf.demo_fbf.entity.Cart;
import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.mapper.CartMapper;
import com.example_fbf.demo_fbf.repository.CartRepository;
import com.example_fbf.demo_fbf.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public boolean AddCartItem(CartItem cartItem) {
        return false;
    }

    @Override
    public Optional<CartDto> findCartById(Long id) {
        return Optional.ofNullable(cartMapper.toDto(cartRepository.findById(id).get()));
    }

    @Override
    public Long getCartIdByUserId(Long userId) {
        return cartRepository.findByFbfUserId(userId)
                .map(Cart::getId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for user ID: " + userId));
    }
}
