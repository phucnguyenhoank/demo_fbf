package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.dto.FoodDto;
import com.example_fbf.demo_fbf.entity.Category;
import com.example_fbf.demo_fbf.entity.Food;
import com.example_fbf.demo_fbf.entity.FoodSize;
import com.example_fbf.demo_fbf.mapper.FoodMapper;
import com.example_fbf.demo_fbf.repository.CategoryRepository;
import com.example_fbf.demo_fbf.repository.FoodRepository;
import com.example_fbf.demo_fbf.repository.FoodSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper; // Injected MapStruct Mapper
    private final FoodSizeRepository foodSizeRepository;
    private final CategoryRepository categoryRepository;

    public List<FoodDto> getAllFoods() {
        return foodRepository.findAll().stream()
                .map(foodMapper::toDto)
                .collect(Collectors.toList());
    }

    public FoodDto getFoodById(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found with id: " + id));
        return foodMapper.toDto(food);
    }

    public List<FoodDto> getFoodsByCategory(String categoryName) {
        List<Food> foods = foodRepository.findByCategoryName(categoryName);
        return foods.stream().map(foodMapper::toDto).collect(Collectors.toList());
    }

    public List<FoodDto> getFoodsByCategories(List<String> categoryNames) {
        List<Food> foods = foodRepository.findByCategoryNameIn(categoryNames);
        return foods.stream().map(foodMapper::toDto).collect(Collectors.toList());
    }

    public FoodDto createFood(FoodDto foodDto) {
        Food food = foodMapper.toEntity(foodDto);
        Food savedFood = foodRepository.save(food);
        return foodMapper.toDto(savedFood);
    }

    public FoodDto updateFood(Long id, FoodDto updatedFoodDto) {
        return foodRepository.findById(id)
                .map(food -> {
                    food.setName(updatedFoodDto.getName());
                    food.setPrice(updatedFoodDto.getPrice());
                    food.setDescription(updatedFoodDto.getDescription());
                    food.setImageUrl(updatedFoodDto.getImageUrl());

                    // Convert foodSizeId to FoodSize entity
                    FoodSize foodSize = foodSizeRepository.findById(updatedFoodDto.getFoodSizeId())
                            .orElseThrow(() -> new RuntimeException("FoodSize not found with id: " + updatedFoodDto.getFoodSizeId()));
                    food.setFoodSize(foodSize);

                    // Convert categoryId to Category entity
                    Category category = categoryRepository.findById(updatedFoodDto.getCategoryId())
                            .orElseThrow(() -> new RuntimeException("Category not found with id: " + updatedFoodDto.getCategoryId()));
                    food.setCategory(category);

                    return foodMapper.toDto(foodRepository.save(food));
                })
                .orElseThrow(() -> new RuntimeException("Food not found with id: " + id));
    }

    public void deleteFood(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found with id: " + id));
        foodRepository.delete(food);
    }
}

