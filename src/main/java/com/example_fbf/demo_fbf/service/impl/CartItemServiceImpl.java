package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.dto.CartItemDto;
import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.mapper.CartItemMapper;
import com.example_fbf.demo_fbf.repository.CartItemRepository;
import com.example_fbf.demo_fbf.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.System.in;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartItemMapper cartItemMapper ;

    public List<CartItemDto> findCartItemByCartId(Long cartId){
        List<CartItem> cartItemList = cartItemRepository.findByCartId(cartId);
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        if (!cartItemList.isEmpty()) {
            CartItemDto cartItemDto = new CartItemDto();
            for (CartItem item : cartItemList) {
                cartItemDto = cartItemMapper.toDto(item);
                cartItemDtos.add(cartItemDto);
            }
        }
        return cartItemDtos;
    }
}
