package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.FoodSizeDto;
import com.example_fbf.demo_fbf.service.FoodSizeService;
import com.example_fbf.demo_fbf.service.impl.FoodSizeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("v1/api/food-size")
public class FoodSizeController {
    @Autowired
    private FoodSizeServiceImpl foodSizeService;

    @GetMapping("/search/foodid-size")
    public Optional<FoodSizeDto> getFoodSizeByFoodIdAndSize(
            @RequestParam(defaultValue = "") Long foodId,
            @RequestParam(defaultValue = "L") String size){
        return foodSizeService.findFoodSizeByFoodIdAndSize(foodId, size);
    }
}
