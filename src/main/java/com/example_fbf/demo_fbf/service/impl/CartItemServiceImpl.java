package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.entity.Cart;
import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.entity.FoodSize;
import com.example_fbf.demo_fbf.repository.CartItemRepository;
import com.example_fbf.demo_fbf.repository.CartRepository;
import com.example_fbf.demo_fbf.repository.FoodSizeRepository;
import com.example_fbf.demo_fbf.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartRepository cartRepository;
    private final FoodSizeRepository foodSizeRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem createCartItem(Long cartId, Long foodSizeId, Integer quantity) {
        // Retrieve Cart by cartId
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for id: " + cartId));

        // Retrieve FoodSize by foodSizeId
        FoodSize foodSize = foodSizeRepository.findById(foodSizeId)
                .orElseThrow(() -> new IllegalArgumentException("FoodSize not found for id: " + foodSizeId));

        // Create new CartItem setting the current price and discount percentage from FoodSize
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setFoodSize(foodSize);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(foodSize.getPrice());
        cartItem.setDiscountPercentage(foodSize.getDiscountPercentage());

        // Save and return the new cart item
        return cartItemRepository.save(cartItem);
    }
}

