package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.entity.CartItem;

public interface CartItemService {
    /**
     * Creates a CartItem by linking the cart (via cartId) and the food size (via foodSizeId)
     * and assigning the quantity. The current price and discount percentage from FoodSize
     * are stored in the CartItem.
     *
     * @param cartId     ID of the cart
     * @param foodSizeId ID of the FoodSize
     * @param quantity   Quantity of the item to add
     * @return the persisted CartItem
     */
    CartItem createCartItem(Long cartId, Long foodSizeId, Integer quantity);

    void deleteCartItem(Long cartId, Long cartItemId);

    CartItem updateCartItem(Long cartId, Long cartItemId, Integer newQuantity, String newSize);
}
