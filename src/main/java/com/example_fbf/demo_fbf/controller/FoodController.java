package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.FoodDto;
import com.example_fbf.demo_fbf.service.FoodService;
import com.example_fbf.demo_fbf.wrapper.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<FoodDto>>> getAllFoods() {
        List<FoodDto> foods = foodService.getAllFoods();
        return ResponseEntity.ok(new ApiResponse<>(true, "Foods retrieved successfully", foods));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FoodDto>> getFoodById(@PathVariable Long id) {
        FoodDto food = foodService.getFoodById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Food found", food));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FoodDto>> createFood(@RequestBody FoodDto foodDto) {
        FoodDto createdFood = foodService.createFood(foodDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Food created successfully", createdFood));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FoodDto>> updateFood(@PathVariable Long id, @RequestBody FoodDto foodDto) {
        FoodDto updatedFood = foodService.updateFood(id, foodDto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Food updated successfully", updatedFood));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Food deleted successfully", null));
    }
}
