package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.dto.CartDto;
import com.example_fbf.demo_fbf.entity.Cart;
import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.mapper.CartMapper;
import com.example_fbf.demo_fbf.repository.CartRepository;
import com.example_fbf.demo_fbf.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartMapper cartMapper;
    @Override
    public boolean AddCartItem(CartItem cartItem) {
        return false;
    }


    @Override
    public Optional<CartDto> findCartById(Long id) {
        return Optional.ofNullable(cartMapper.toDto(cartRepository.findById(id).get()));
    }
}
