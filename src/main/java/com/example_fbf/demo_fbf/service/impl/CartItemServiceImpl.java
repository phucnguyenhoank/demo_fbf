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
import com.example_fbf.demo_fbf.mapper.CartItemMapper;
import com.example_fbf.demo_fbf.dto.CartItemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartRepository cartRepository;
    private final FoodSizeRepository foodSizeRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public CartItem createCartItem(Long cartId, Long foodSizeId, Integer quantity) {
        // Retrieve Cart by cartId
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for id: " + cartId));

        // Retrieve FoodSize by foodSizeId
        FoodSize foodSize = foodSizeRepository.findById(foodSizeId)
                .orElseThrow(() -> new IllegalArgumentException("FoodSize not found for id: " + foodSizeId));

        // Look for an existing CartItem in the cart for the given FoodSize
        Optional<CartItem> existingCartItemOptional = cart.getItems().stream()
                .filter(item -> item.getFoodSize().getId().equals(foodSizeId))
                .findFirst();

        CartItem cartItem;
        if (existingCartItemOptional.isPresent()) {
            // Update existing CartItem
            cartItem = existingCartItemOptional.get();
            int newTotalQuantity = cartItem.getQuantity() + quantity;
            // Check if new total quantity exceeds available stock
            if (foodSize.getStock() < newTotalQuantity) {
                throw new IllegalArgumentException("Not enough stock for size " + foodSize.getSize() +
                        ". Available: " + foodSize.getStock() + ", Requested: " + newTotalQuantity);
            }
            cartItem.setQuantity(newTotalQuantity);
        } else {
            // Check if available stock is enough for new CartItem
            if (foodSize.getStock() < quantity) {
                throw new IllegalArgumentException("Not enough stock for size " + foodSize.getSize() +
                        ". Available: " + foodSize.getStock() + ", Requested: " + quantity);
            }
            // Create new CartItem
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setFoodSize(foodSize);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(foodSize.getPrice());
            cartItem.setDiscountPercentage(foodSize.getDiscountPercentage());
        }

        // Save and return the updated or new CartItem
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

        // Find new FoodSize based on the existing food and the provided size code
        Long foodId = cartItem.getFoodSize().getFood().getId();
        FoodSize newFoodSize = foodSizeRepository.findByFoodIdAndSize(foodId, newSize)
                .orElseThrow(() -> new IllegalArgumentException("FoodSize not found for food id " + foodId + " and size " + newSize));
        if (newFoodSize.getStock() < newQuantity) {
            throw new IllegalArgumentException("Not enough stock for size " + newFoodSize.getSize());
        }

        // Update quantity
        cartItem.setQuantity(newQuantity);

        // Update foodSize and capture the price and discount at the time of change
        cartItem.setFoodSize(newFoodSize);
        cartItem.setPrice(newFoodSize.getPrice());
        cartItem.setDiscountPercentage(newFoodSize.getDiscountPercentage());

        return cartItemRepository.save(cartItem);
    }

    @Override
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

    @Override
    public Optional<CartItemDto> findCartItemByCartIdAndFoodSizeId(Long cartId, Long foodSizeId) {
        return Optional.ofNullable(cartItemMapper.toDto(cartItemRepository.findByCartIdAndFoodSizeId(cartId, foodSizeId).get()));
    }

    @Override
    public List<CartItem> findByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }



}


