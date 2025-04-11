package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.ApiResponse;
import com.example_fbf.demo_fbf.dto.CartItemDto;
import com.example_fbf.demo_fbf.dto.CartItemRequest;
import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.mapper.CartItemMapper;
import com.example_fbf.demo_fbf.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartItemDto>> addCartItem(@RequestBody CartItemRequest request) {
        CartItem createdCartItem = cartItemService.createCartItem(
                request.getCartId(),
                request.getFoodSizeId(),
                request.getQuantity());
        CartItemDto cartItemDto = cartItemMapper.toDto(createdCartItem);
        return ResponseEntity.ok(new ApiResponse<>(true, "Created CartItem", cartItemDto));
    }
}

