package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.entity.CartItem;
import org.springframework.stereotype.Service;

@Service
public interface CartService{
    public boolean AddCartItem(CartItem cartItem);
}
