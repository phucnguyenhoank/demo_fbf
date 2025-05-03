package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.config.JwtService;
import com.example_fbf.demo_fbf.dto.*;
import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.mapper.CartItemMapper;
import com.example_fbf.demo_fbf.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CartItemDto>>> getAllCartItemInCart(
            @RequestHeader("Authorization") String authHeader) {

        // Extract JWT token
        String token = authHeader.substring(7);
        Long cartId = jwtService.getCartIdFromToken(token);

        // Get CartItems by cartId
        List<CartItem> cartItems = cartItemService.findByCartId(cartId);

        // Map to DTOs
        List<CartItemDto> cartItemDtos = cartItems.stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "Cart items fetched", cartItemDtos));
    }

    @GetMapping("/display")
    public ResponseEntity<ApiResponse<List<CartItemDisplayDto>>> getCartDisplay(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        Long cartId = jwtService.getCartIdFromToken(token);

        List<CartItem> cartItems = cartItemService.findByCartId(cartId);
        List<CartItemDisplayDto> displayDtos = cartItems.stream()
                .map(cartItemMapper::toDisplayDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "Cart items fetched", displayDtos));
    }



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

    @GetMapping("/search/by-cart-id")
    public List<CartItemDto> findCartItemByCartId(@RequestParam(defaultValue = "0") Long id){
        List<CartItemDto> cartItemList = cartItemService.findCartItemByCartId(id);
        return cartItemList;
    }

    @GetMapping("/search/by-cart-foodsize")
    public ResponseEntity<CartItemDto> getCartItemByCartIdAndSize(
            @RequestParam Long cartId,
            @RequestParam Long foodSizeid
    )
    {
        Optional<CartItemDto> cartItemDto = cartItemService.findCartItemByCartIdAndFoodSizeId(cartId, foodSizeid);
        return cartItemDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

