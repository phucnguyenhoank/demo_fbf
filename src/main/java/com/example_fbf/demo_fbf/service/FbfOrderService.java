package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.entity.FbfOrder;

import java.util.List;

public interface FbfOrderService {
    /**
     * Create an order from the selected CartItems in the user's cart.
     *
     * @param fbfUserId           The ID of the FbfUser.
     * @param phoneNumber         The contact phone number.
     * @param address             The delivery address.
     * @param selectedCartItemIds The list of CartItem IDs the user has selected for ordering.
     * @return The created FbfOrder.
     */
    FbfOrder createOrder(Long fbfUserId, String phoneNumber, String address, List<Long> selectedCartItemIds);

    /**
     * Undo the creation of an order.
     * This method restores FoodSize stock, re-adds CartItems from the order into the user's cart,
     * and deletes the order.
     *
     * @param orderId the ID of the order to undo
     */
    void undoOrder(Long orderId);
}

