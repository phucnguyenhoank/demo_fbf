package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.ApiResponse;
import com.example_fbf.demo_fbf.dto.FbfOrderDto;
import com.example_fbf.demo_fbf.dto.OrderRequest;
import com.example_fbf.demo_fbf.entity.FbfOrder;
import com.example_fbf.demo_fbf.mapper.FbfOrderMapper;
import com.example_fbf.demo_fbf.service.FbfOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fbf-orders")
@RequiredArgsConstructor
public class FbfOrderController {

    private final FbfOrderService fbfOrderService;
    private final FbfOrderMapper fbfOrderMapper;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<FbfOrderDto>> createOrder(@RequestBody OrderRequest request) {
        FbfOrder order = fbfOrderService.createOrder(
                request.getFbfUserId(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getSelectedCartItemIds()
        );
        FbfOrderDto fbfOrderDto = fbfOrderMapper.toDto(order);
        return ResponseEntity.ok(new ApiResponse<>(true, "Successfully created FbfOrder", fbfOrderDto));
    }
}

