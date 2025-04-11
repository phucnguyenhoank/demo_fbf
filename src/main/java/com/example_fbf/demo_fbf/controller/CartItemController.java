package com.example_fbf.demo_fbf.controller;

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

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartItemDto>> addCartItem(@RequestBody CartItemRequest request) {
        CartItem createdCartItem = cartItemService.createCartItem(
                request.getCartId(),
                request.getFoodSizeId(),
                request.getQuantity());
        CartItemDto cartItemDto = cartItemMapper.toDto(createdCartItem);
        return ResponseEntity.ok(new ApiResponse<>(true, "Created CartItem", cartItemDto));
    }

    @DeleteMapping("/{cartId}/{cartItemId}")
    public ResponseEntity<ApiResponse<String>> deleteCartItem(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartId, cartItemId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Cart item deleted successfully", "No addition"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<CartItemDto>> updateCartItem(@RequestBody CartItemUpdateRequest updateRequest) {
        CartItem updatedCartItem = cartItemService.updateCartItem(
                updateRequest.getCartId(),
                updateRequest.getCartItemId(),
                updateRequest.getNewQuantity(),
                updateRequest.getNewSize());
        CartItemDto cartItemDto = cartItemMapper.toDto(updatedCartItem);
        return ResponseEntity.ok(new ApiResponse<>(true, "Updated CartItem", cartItemDto));
    }
}

