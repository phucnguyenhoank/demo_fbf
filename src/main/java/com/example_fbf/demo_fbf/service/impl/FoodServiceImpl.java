package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.dto.FoodDto;
import com.example_fbf.demo_fbf.entity.Food;
import com.example_fbf.demo_fbf.mapper.FoodMapper;
import com.example_fbf.demo_fbf.repository.FoodRepository;
import com.example_fbf.demo_fbf.service.FoodService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodMapper foodMapper;


    @Override
    public Page<FoodDto> findByContainName(PageRequest pageRequest,String namePattern) {
        List<Food> foodList;
        Page<Food> foodPage;
        FoodDto foodDto = new FoodDto();
        List<FoodDto> foodDtos = new ArrayList<>();
        foodPage = foodRepository.findByNameContainingIgnoreCase(pageRequest,namePattern);
        foodList = foodPage.getContent();
        for(int i = 0 ; i < foodList.size() ; i++){
            foodDto = foodMapper.toDto(foodList.get(i));
            foodDtos.add(foodDto);
        }
        return new PageImpl<>(foodDtos, foodPage.getPageable(), foodPage.getTotalElements());
    }

    @Override
    public Optional<Food> findFoodById(Long id) {
        return foodRepository.findById(id);
    }

    @Override
    public Page<FoodDto> findFoodByCategoryId(PageRequest pageRequest, Long id) {
        Page<Food> foodPage = foodRepository.findByCategoryId(pageRequest, id);
        List<Food> foodList = foodPage.getContent();
        List<FoodDto> foodDtos = new ArrayList<>();
        FoodDto foodDto = new FoodDto();
        for(Food item : foodList){
            foodDto = foodMapper.toDto(item);
            foodDtos.add(foodDto);
        }
        return new PageImpl<>(foodDtos, foodPage.getPageable(), foodPage.getTotalElements());

    }

    @Override
    public Page<FoodDto> findAllFood(PageRequest pageRequest) {
        Page<Food> foodPage = foodRepository.findAll(pageRequest);
        List<Food> foodList = foodPage.getContent();
        List<FoodDto> foodDtos = new ArrayList<>();
        FoodDto foodDto = new FoodDto();
        for(Food item : foodList){
            foodDto = foodMapper.toDto(item);
            foodDtos.add(foodDto);
        }
        return new PageImpl<>(foodDtos, foodPage.getPageable(), foodPage.getTotalElements());
    }

    @Override
    public Page<FoodDto> findFoodByPriceBetween(Pageable pageable, Double min, Double max) {
        Page<Food> foodPage = foodRepository.findFoodsBySizePriceBetween(pageable, min, max);
        List<Food> foodList = foodPage.getContent();
        List<FoodDto> foodDtoList = new ArrayList<>();
        FoodDto foodDto = new FoodDto();
        for(Food item : foodList){
            foodDto = foodMapper.toDto(item);
            foodDtoList.add(foodDto);
        }
        return new PageImpl<>(foodDtoList, foodPage.getPageable(), foodPage.getTotalElements());
    }
}
