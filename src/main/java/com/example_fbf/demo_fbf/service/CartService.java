package com.example_fbf.demo_fbf.service;
import com.example_fbf.demo_fbf.dto.CartDto;
import com.example_fbf.demo_fbf.entity.CartItem;

import java.util.Optional;

public interface CartService {
    Long getCartIdByUserId(Long userId);
    boolean AddCartItem(CartItem cartItem);
    Optional<CartDto> findCartById(Long id);
}
