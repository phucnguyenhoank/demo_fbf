package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.config.JwtService;
import com.example_fbf.demo_fbf.dto.ApiResponse;
import com.example_fbf.demo_fbf.dto.FbfOrderDto;
import com.example_fbf.demo_fbf.dto.OrderRequest;
import com.example_fbf.demo_fbf.entity.FbfOrder;
import com.example_fbf.demo_fbf.mapper.FbfOrderMapper;
import com.example_fbf.demo_fbf.service.FbfOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fbf-orders")
@RequiredArgsConstructor
public class FbfOrderController {

    private final FbfOrderService fbfOrderService;
    private final FbfOrderMapper fbfOrderMapper;
    private final JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<FbfOrderDto>> createOrder(@RequestHeader("Authorization") String authHeader, @RequestBody OrderRequest request) {
        String token = authHeader.substring(7);
        Long fbfUserId = jwtService.getFbfUserIdFromToken(token);
        FbfOrder order = fbfOrderService.createOrder(
                fbfUserId,
                request.getPhoneNumber(),
                request.getAddress(),
                request.getSelectedCartItemIds(),
                request.getDiscountCode()
        );
        FbfOrderDto fbfOrderDto = fbfOrderMapper.toDto(order);
        return ResponseEntity.ok(new ApiResponse<>(true, "Successfully created FbfOrder", fbfOrderDto));
    }

    @DeleteMapping("/{orderId}/undo")
    public ResponseEntity<ApiResponse<String>> undoOrder(@RequestHeader("Authorization") String authHeader, @PathVariable Long orderId) {
        String token = authHeader.substring(7);
        Long fbfUserId = jwtService.getFbfUserIdFromToken(token);
        fbfOrderService.undoOrder(fbfUserId, orderId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Order undone successfully", null));
    }

    @PostMapping("/create-undo")
    public ResponseEntity<ApiResponse<FbfOrderDto>> createUndoOrder(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody OrderRequest request) {
        // Lấy token và userId
        String token = authHeader.substring(7);
        Long fbfUserId = jwtService.getFbfUserIdFromToken(token);

        // Tạo order dạng UndoOrder (tuỳ business logic của bạn)
        FbfOrder order = fbfOrderService.createUndoOrder(
                fbfUserId,
                request.getPhoneNumber(),
                request.getAddress(),
                request.getSelectedCartItemIds(),
                request.getDiscountCode()
        );

        FbfOrderDto dto = fbfOrderMapper.toDto(order);
        return ResponseEntity.ok(new ApiResponse<>(true, "Created Undo Order", dto));
    }

    @PostMapping("/{orderId}/confirm")
    public ResponseEntity<ApiResponse<String>> confirmOrder(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long orderId) {
        String token = authHeader.substring(7);
        Long fbfUserId = jwtService.getFbfUserIdFromToken(token);

        fbfOrderService.confirmOrder(fbfUserId, orderId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Order confirmed", null));
    }

    @GetMapping("/get-mine")
    private Page<FbfOrderDto> getAllFbfOrdersByFbfUserId(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort
    ) {
        String token = authHeader.substring(7);
        Long fbfUserId = jwtService.getFbfUserIdFromToken(token);

        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));

        return fbfOrderService.getAllFbfOrdersByFbfUserId(pageRequest, fbfUserId);
    }

    @GetMapping("/get")
    private ApiResponse<FbfOrderDto> getOrderByOrderId(@RequestParam(defaultValue = "-1") Long orderId){
        Optional<FbfOrder> order = fbfOrderService.findOrderByOrderId(orderId);
        ApiResponse<FbfOrderDto> orderDtoApiResponse = new ApiResponse<>();
        if (!order.isEmpty()){
        FbfOrderDto orderDto = fbfOrderMapper.toDto(order.get());
        orderDtoApiResponse.setData(orderDto);
        orderDtoApiResponse.setSuccess(true);
        orderDtoApiResponse.setMessage("Order Controller: Successful");
        }
        else {
            orderDtoApiResponse.setSuccess(false);
            orderDtoApiResponse.setMessage("Order Controller: Failed");
        }
        return orderDtoApiResponse;
    }

}

