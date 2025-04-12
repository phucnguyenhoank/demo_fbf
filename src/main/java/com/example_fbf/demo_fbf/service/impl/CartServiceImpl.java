package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Override
    public boolean AddCartItem(CartItem cartItem) {
        return false;
    }
}
