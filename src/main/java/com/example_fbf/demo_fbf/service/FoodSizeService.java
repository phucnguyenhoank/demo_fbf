package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.dto.FoodSizeDto;
import com.example_fbf.demo_fbf.entity.FoodSize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FoodSizeService {
    public Optional<FoodSizeDto> findFoodSizeByFoodIdAndSize(Long foodId, String size);
}
