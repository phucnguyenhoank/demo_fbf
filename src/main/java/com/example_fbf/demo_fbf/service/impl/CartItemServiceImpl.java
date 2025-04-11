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

    @Override
    public void deleteCartItem(Long cartId, Long cartItemId) {
        // Verify the cart exists
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for id: " + cartId));

        // Verify the cart item exists and belongs to the cart
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("CartItem not found for id: " + cartItemId));

        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new IllegalArgumentException("CartItem does not belong to this cart");
        }

        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long cartId, Long cartItemId, Integer newQuantity, String newSize) {
        // Verify the cart exists
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for id: " + cartId));

        // Retrieve the cart item and check ownership
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("CartItem not found for id: " + cartItemId));
        if (!cartItem.getCart().getId().equals(cart.getId())) {
            throw new IllegalArgumentException("CartItem does not belong to this cart");
        }

        // Update quantity
        cartItem.setQuantity(newQuantity);

        // Find new FoodSize based on the existing food and the provided size code
        Long foodId = cartItem.getFoodSize().getFood().getId();
        FoodSize newFoodSize = foodSizeRepository.findByFoodIdAndSize(foodId, newSize)
                .orElseThrow(() -> new IllegalArgumentException("FoodSize not found for food id " + foodId + " and size " + newSize));

        // Update foodSize and capture the price and discount at the time of change
        cartItem.setFoodSize(newFoodSize);
        cartItem.setPrice(newFoodSize.getPrice());
        cartItem.setDiscountPercentage(newFoodSize.getDiscountPercentage());

        return cartItemRepository.save(cartItem);
    }
}

