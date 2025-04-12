package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.dto.CartItemDto;
import com.example_fbf.demo_fbf.entity.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CartItemService {
    public List<CartItemDto> findCartItemByCartId(Long id);
}
