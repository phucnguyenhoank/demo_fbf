package com.example_fbf.demo_fbf.service;
import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.dto.CartItemDto;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    CartItem createCartItem(Long cartId, Long foodSizeId, Integer quantity);
    void deleteCartItem(Long cartId, Long cartItemId);
    CartItem updateCartItem(Long cartId, Long cartItemId, Integer newQuantity, String newSize);
    
    List<CartItemDto> findCartItemByCartId(Long id);
    Optional<CartItemDto> findCartItemByCartIdAndFoodSizeId(Long cartId, Long foodSizeId);

    List<CartItem> findByCartId(Long cartId);
}
