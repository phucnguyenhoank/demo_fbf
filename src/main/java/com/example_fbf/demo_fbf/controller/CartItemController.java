package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.config.JwtService;
import com.example_fbf.demo_fbf.dto.ApiResponse;
import com.example_fbf.demo_fbf.dto.CartItemDto;
import com.example_fbf.demo_fbf.dto.CartItemRequest;
import com.example_fbf.demo_fbf.dto.CartItemUpdateRequest;
import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.mapper.CartItemMapper;
import com.example_fbf.demo_fbf.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;
    private final JwtService jwtService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartItemDto>> addCartItem(@RequestHeader("Authorization") String authHeader, @RequestBody CartItemRequest request) {
        String token = authHeader.substring(7);
        Long cartId = jwtService.getCartIdFromToken(token);
        CartItem createdCartItem = cartItemService.createCartItem(
                cartId,
                request.getFoodSizeId(),
                request.getQuantity());
        CartItemDto cartItemDto = cartItemMapper.toDto(createdCartItem);
        return ResponseEntity.ok(new ApiResponse<>(true, "Created CartItem", cartItemDto));
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse<String>> deleteCartItem(@RequestHeader("Authorization") String authHeader, @PathVariable Long cartItemId) {
        String token = authHeader.substring(7);
        Long cartId = jwtService.getCartIdFromToken(token);
        cartItemService.deleteCartItem(cartId, cartItemId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cart item deleted successfully", "No addition"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<CartItemDto>> updateCartItem(@RequestHeader("Authorization") String authHeader, @RequestBody CartItemUpdateRequest updateRequest) {
        String token = authHeader.substring(7);
        Long cartId = jwtService.getCartIdFromToken(token);
        CartItem updatedCartItem = cartItemService.updateCartItem(
                cartId,
                updateRequest.getCartItemId(),
                updateRequest.getNewQuantity(),
                updateRequest.getNewSize());
        CartItemDto cartItemDto = cartItemMapper.toDto(updatedCartItem);
        return ResponseEntity.ok(new ApiResponse<>(true, "Updated CartItem", cartItemDto));
    }
}

