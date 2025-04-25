package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.entity.FbfOrder;
import com.example_fbf.demo_fbf.dto.FbfOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;

public interface FbfOrderService {
    FbfOrder createOrder(Long fbfUserId, String phoneNumber, String address, List<Long> selectedCartItemIds, String discountCode);

    void undoOrder(Long fbfUserId, Long orderId);

    Page<FbfOrderDto> getAllOrderByOrderId(PageRequest pageRequest, Long id);

}
