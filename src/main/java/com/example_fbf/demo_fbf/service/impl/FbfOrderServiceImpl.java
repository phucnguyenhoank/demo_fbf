package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.config.OrderUndoScheduler;
import com.example_fbf.demo_fbf.dto.FbfOrderDto;
import com.example_fbf.demo_fbf.entity.*;
import com.example_fbf.demo_fbf.mapper.FbfOrderMapper;
import com.example_fbf.demo_fbf.repository.*;
import com.example_fbf.demo_fbf.service.FbfOrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional  // Đảm bảo toàn bộ quá trình được rollback nếu có lỗi
public class FbfOrderServiceImpl implements FbfOrderService {
    private final ApplicationContext applicationContext;
    private final FbfOrderRepository fbfOrderRepository;
    private final FbfUserRepository fbfUserRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodSizeRepository foodSizeRepository;
    private final CartRepository cartRepository;
    private final DiscountCodeRepository discountCodeRepository;
    private final FbfOrderMapper fbfOrderMapper;

    private List<FbfOrder> fbfOrder;
    private List<FbfOrderDto> fbfOrderDtoList = new ArrayList<>();

    @Override
    public FbfOrder createOrder(Long fbfUserId, String phoneNumber, String address, List<Long> selectedCartItemIds, String discountCode) {
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
            orderItem.setDiscountedPrice(discountedUnitPrice);
            orderItems.add(orderItem);

            totalOrderPrice += discountedUnitPrice * aggregatedQuantity;
        }

        // Apply discount code if provided and valid
        double finalPrice = totalOrderPrice;
        DiscountCode appliedDiscountCode = null;
        if (discountCode != null && !discountCode.isEmpty()) {
            DiscountCode discountCodeEntity = discountCodeRepository.findByCode(discountCode)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid discount code: " + discountCode));
            if (discountCodeEntity.getExpirationDate() != null && discountCodeEntity.getExpirationDate().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Discount code has expired: " + discountCode);
            }
            double discountPercentage = discountCodeEntity.getDiscountPercentage();
            finalPrice = totalOrderPrice * (1 - discountPercentage / 100.0);
            appliedDiscountCode = discountCodeEntity;
        }

        FbfOrder fbfOrder = new FbfOrder();
        fbfOrder.setFbfUser(fbfUser);
        fbfOrder.setPhoneNumber(phoneNumber);
        fbfOrder.setAddress(address);
        fbfOrder.setCreatedAt(LocalDateTime.now());
        fbfOrder.setDiscountedTotalPrice(finalPrice);
        fbfOrder.setDiscountCode(appliedDiscountCode);
        // Set items
        for (OrderItem item : orderItems) {
            item.setFbfOrder(fbfOrder);
        }
        fbfOrder.setItems(orderItems);

        // Lưu đơn hàng
        FbfOrder savedOrder = fbfOrderRepository.save(fbfOrder);
        cartItemRepository.deleteAll(selectedCartItems);
        savedOrder.setItems(orderItems); // Optional: to reflect changes in response
        return savedOrder;
    }

    @Override
    public FbfOrder undoOrder(Long fbfUserId, Long orderId) {
        // Retrieve the order to be canceled
        FbfOrder order = fbfOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));

        if (!order.getFbfUser().getId().equals(fbfUserId)) {
            throw new IllegalStateException("Unauthorized: Order does not belong to the user");
        }

        // For each OrderItem, reverse the stock reduction and re-create a corresponding CartItem
        for (OrderItem orderItem : order.getItems()) {
            FoodSize foodSize = orderItem.getFoodSize();
            int quantity = orderItem.getQuantity();

            // Reverse stock reduction
            foodSize.setStock(foodSize.getStock() + quantity);
            foodSizeRepository.save(foodSize);

            // Re-create a CartItem
            FbfUser user = order.getFbfUser();
            Cart cart = user.getCart();
            if (cart == null) {
                cart = new Cart();
                cart.setFbfUser(user);
                cart = cartRepository.save(cart);
            }

            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setFoodSize(foodSize);
            cartItem.setQuantity(quantity);
            cartItem.setPrice(foodSize.getPrice());
            cartItem.setDiscountPercentage(orderItem.getDiscountPercentage());

            cartItemRepository.save(cartItem);
        }

        // Delete the order, which removes the discount code association
//        fbfOrderRepository.delete(order);

        order.setStatus(FbfOrderStatus.CANCELED);
        return fbfOrderRepository.save(order);
    }

    @Override
    public Page<FbfOrderDto> getAllFbfOrdersByFbfUserId(PageRequest pageRequest, Long fbfUserId) {
        Page<FbfOrder> fbfOrderPage = fbfOrderRepository.findByFbfUser_Id(pageRequest, fbfUserId);

        List<FbfOrderDto> dtoList = fbfOrderPage
                .getContent()
                .stream()
                .map(fbfOrderMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, fbfOrderPage.getPageable(), fbfOrderPage.getTotalElements());
    }

    @Override
    public FbfOrder createUndoOrder(Long fbfUserId, String phoneNumber, String address, List<Long> selectedCartItemIds, String discountCode) {
        FbfOrder createdFbfOrder = createOrder(fbfUserId, phoneNumber, address, selectedCartItemIds, discountCode);
        Instant triggerTime = Instant.now().plus(3, ChronoUnit.MINUTES);
        OrderUndoScheduler scheduler = applicationContext.getBean(OrderUndoScheduler.class);
        scheduler.scheduleUndoOrder(createdFbfOrder.getId(), triggerTime);
        return createdFbfOrder;
    }

    @Override
    public void confirmOrder(Long userId, Long orderId) {
        FbfOrder o = fbfOrderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        if (o.getFbfUser().getId().equals(userId)
                && o.getStatus() == FbfOrderStatus.PENDING) {
            o.setStatus(FbfOrderStatus.PAID);
            fbfOrderRepository.save(o);
        }
    }

    @Override
    public Optional<FbfOrder> findOrderByOrderId(Long orderId) {
        return fbfOrderRepository.findById(orderId);
    }

    /**
     * Copy thông tin của một fbfOrder đã có sẵn và đặt trạng thái là CANCELED
     * @param createdFbfOrderId
     * @return FbfOrder đã tạo
     */
    @Override
    public FbfOrder createCanceledOrder(Long createdFbfOrderId) {
        FbfOrder o = fbfOrderRepository.findById(createdFbfOrderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not created to be canceled"));
        o.setStatus(FbfOrderStatus.CANCELED);
        return fbfOrderRepository.save(o);
    }

    @Override
    public void deleteCanceledOrder(Long fbfUserId, Long orderId) {
        FbfOrder order = fbfOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));

        if (order.getStatus() != FbfOrderStatus.CANCELED) {
            throw new IllegalStateException("Unauthorized: Order but be canceled to be deleted");
        }

        if (!order.getFbfUser().getId().equals(fbfUserId)) {
            throw new IllegalStateException("Unauthorized: Order does not belong to the user");
        }

        fbfOrderRepository.delete(order);
    }

}

