package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.dto.FoodSizeDto;
import com.example_fbf.demo_fbf.mapper.FoodSizeMapper;
import com.example_fbf.demo_fbf.repository.FoodSizeRepository;
import com.example_fbf.demo_fbf.service.FoodSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodSizeServiceImpl implements FoodSizeService {
    @Autowired
    private FoodSizeRepository foodSizeRepository;
    @Autowired
    private FoodSizeMapper foodSizeMapper;

    @Override
    public Optional<FoodSizeDto> findFoodSizeByFoodIdAndSize(Long foodId, String size) {
        return Optional.ofNullable(foodSizeMapper.toDto(foodSizeRepository.findByFoodIdAndSize(foodId, size).get()));
    }
}
