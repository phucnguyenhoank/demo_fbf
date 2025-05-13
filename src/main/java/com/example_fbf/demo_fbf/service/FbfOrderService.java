package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.entity.FbfOrder;
import com.example_fbf.demo_fbf.dto.FbfOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

public interface FbfOrderService {
    FbfOrder createOrder(Long fbfUserId, String phoneNumber, String address, List<Long> selectedCartItemIds, String discountCode);

    void undoOrder(Long fbfUserId, Long orderId);

    Page<FbfOrderDto> getAllFbfOrdersByFbfUserId(PageRequest pageRequest, Long fbfUserId);

    FbfOrder createUndoOrder(Long fbfUserId, String phoneNumber, String address, List<Long> selectedCartItemIds, String discountCode);

    void confirmOrder(Long userId, Long orderId);

    Optional<FbfOrder> findOrderByOrderId(Long orderId);
}
