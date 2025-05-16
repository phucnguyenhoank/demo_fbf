package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.config.JwtService;
import com.example_fbf.demo_fbf.dto.ApiResponse;
import com.example_fbf.demo_fbf.dto.FbfUserDto;
import com.example_fbf.demo_fbf.dto.UpdateFbfUserRequest;
import com.example_fbf.demo_fbf.entity.FbfUser;
import com.example_fbf.demo_fbf.mapper.FbfUserMapper;
import com.example_fbf.demo_fbf.service.FbfUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class FbfUserController {

    private final FbfUserService fbfUserService;
    private final FbfUserMapper fbfUserMapper;
    private final JwtService jwtService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<FbfUserDto>> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        Long fbfUserId = jwtService.getFbfUserIdFromToken(token);
        FbfUserDto fbfUserDto = fbfUserMapper.toDto(fbfUserService.getUserById(fbfUserId));
        return ResponseEntity.ok(new ApiResponse<>(true, "Successfully retrieved user information", fbfUserDto));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<FbfUserDto>> updateCurrentUser(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody UpdateFbfUserRequest request
    ) {
        String token = authHeader.substring(7);
        Long fbfUserId = jwtService.getFbfUserIdFromToken(token);

        FbfUser oldFbfUser = fbfUserService.getUserById(fbfUserId);
        oldFbfUser.setPhoneNumber(request.getPhoneNumber());
        oldFbfUser.setAddress(request.getAddress());

        // Cập nhật thông tin người dùng
        FbfUser updatedUser = fbfUserService.updateUser(oldFbfUser);

        // Chuyển sang DTO để trả về
        FbfUserDto fbfUserDto = fbfUserMapper.toDto(updatedUser);

        return ResponseEntity.ok(new ApiResponse<>(true, "Successfully updated user information", fbfUserDto));
    }



}