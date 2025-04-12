package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.dto.CartDto;
import com.example_fbf.demo_fbf.entity.Cart;
import com.example_fbf.demo_fbf.entity.CartItem;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CartService{
    public boolean AddCartItem(CartItem cartItem);
    public Optional<CartDto> findCartById(Long id);
}
