package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.entity.*;
import com.example_fbf.demo_fbf.repository.*;
import com.example_fbf.demo_fbf.service.FbfOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional  // Đảm bảo toàn bộ quá trình được rollback nếu có lỗi
public class FbfOrderServiceImpl implements FbfOrderService {

    private final FbfOrderRepository fbfOrderRepository;
    private final FbfUserRepository fbfUserRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodSizeRepository foodSizeRepository;

    @Override
    public FbfOrder createOrder(Long fbfUserId, String phoneNumber, String address, List<Long> selectedCartItemIds) {
        // Lấy FbfUser và giỏ hàng
        FbfUser fbfUser = fbfUserRepository.findById(fbfUserId)
                .orElseThrow(() -> new IllegalArgumentException("FbfUser not found with id: " + fbfUserId));

        Cart cart = fbfUser.getCart();
        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty for user id: " + fbfUserId);
        }

        List<CartItem> allCartItems = cart.getItems();
        List<CartItem> selectedCartItems = (selectedCartItemIds == null || selectedCartItemIds.isEmpty())
                ? allCartItems
                : allCartItems.stream()
                .filter(item -> selectedCartItemIds.contains(item.getId()))
                .toList();
        if (selectedCartItems.isEmpty()) {
            throw new RuntimeException("No cart items selected");
        }

        Map<Long, List<CartItem>> groupedItems = selectedCartItems.stream()
                .collect(Collectors.groupingBy(item -> item.getFoodSize().getId()));

        List<OrderItem> orderItems = new ArrayList<>();
        double totalOrderPrice = 0.0;
        for (Map.Entry<Long, List<CartItem>> entry : groupedItems.entrySet()) {
            Long foodSizeId = entry.getKey();
            List<CartItem> itemsForFoodSize = entry.getValue();

            int aggregatedQuantity = itemsForFoodSize.stream()
                    .mapToInt(CartItem::getQuantity)
                    .sum();

            FoodSize foodSize = itemsForFoodSize.getFirst().getFoodSize();

            if (foodSize.getStock() < aggregatedQuantity) {
                throw new IllegalArgumentException("Not enough stock for food size "
                        + foodSize.getSize() + " of food " + foodSize.getFood().getName());
            }
            foodSize.setStock(foodSize.getStock() - aggregatedQuantity);
            foodSizeRepository.save(foodSize);

            Double discountPercentage = foodSize.getDiscountPercentage();
            if (discountPercentage == null) {
                discountPercentage = 0.0;
            }

            double unitPrice = foodSize.getPrice();
            double discountedUnitPrice = unitPrice * (1 - discountPercentage / 100.0);
            discountedUnitPrice = new BigDecimal(discountedUnitPrice)
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue();


            OrderItem orderItem = new OrderItem();
            orderItem.setFoodSize(foodSize);
            orderItem.setQuantity(aggregatedQuantity);
            orderItem.setDiscountPercentage(discountPercentage);
            orderItem.setDiscountedPrice(discountedUnitPrice); // Giá đơn vị
            orderItems.add(orderItem);

            totalOrderPrice += discountedUnitPrice * aggregatedQuantity;
        }

        FbfOrder fbfOrder = new FbfOrder();
        fbfOrder.setFbfUser(fbfUser);
        fbfOrder.setPhoneNumber(phoneNumber);
        fbfOrder.setAddress(address);
        fbfOrder.setCreatedAt(LocalDateTime.now());
        fbfOrder.setDiscountedTotalPrice(totalOrderPrice);
        // Set items → will auto-save if cascade is enabled
        for (OrderItem item : orderItems) {
            item.setFbfOrder(fbfOrder);
        }
        fbfOrder.setItems(orderItems);

        // Lưu đơn hàng
        FbfOrder savedOrder = fbfOrderRepository.save(fbfOrder);
        // Xóa hết các CartItem đã xử lý (tùy vào nghiệp vụ, có thể xóa toàn bộ giỏ hàng)
        cartItemRepository.deleteAll(selectedCartItems);
        savedOrder.setItems(orderItems); // optional: to reflect changes in response or service
        return savedOrder;
    }
}

