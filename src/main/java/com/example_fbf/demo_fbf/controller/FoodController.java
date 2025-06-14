package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.ApiResponse;
import com.example_fbf.demo_fbf.dto.FoodDto;
import com.example_fbf.demo_fbf.entity.Food;
import com.example_fbf.demo_fbf.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FoodDto>> getFoodDetailsById(@PathVariable("id") Long id) {
        FoodDto dto = foodService.getFoodById(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lấy thông tin món ăn thành công", dto)
        );
    }

    @GetMapping("/search/by-name")
    public Page<FoodDto> findFoodByNameKeywords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort,
            @RequestParam(defaultValue = "") String keyword
    ){
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));

        return foodService.findByContainName(pageRequest, keyword);
    }
    @GetMapping("/search/by-id")
    public ResponseEntity<Food> findFoodByFoodId(
            @RequestParam(defaultValue = "") Long id
    )
    {
        return foodService.findFoodById(id).map(ResponseEntity :: ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/search/category-id")
    public Page<FoodDto> findFoodByCategoryId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort,
            @RequestParam(defaultValue = "1") Long id
    ){
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC :Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));
        return foodService.findFoodByCategoryId(pageRequest, id);
    }
    @GetMapping("/all")
    public Page<FoodDto> getAllFoods(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort
    ){
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));
        return foodService.findAllFood(pageRequest);
    }
    @GetMapping("/search/by-price")
    public Page<FoodDto> getFoodByPriceBetween(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort,
            @RequestParam(defaultValue = "0") Double min,
            @RequestParam(defaultValue = "10000000") Double max
    )
    {
        String[]sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));
        return foodService.findFoodByPriceBetween(pageRequest, min, max);
    }

    @GetMapping("/search/full")
    public Page<FoodDto> getFoodByFullFilter(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort,
            @RequestParam(defaultValue = "0") Double min,
            @RequestParam(defaultValue = "999999") Double max,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") Long categoryId
    ) {
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));
        return foodService.findFoodByConditions(pageRequest, min, max, name, categoryId);
    }

}
