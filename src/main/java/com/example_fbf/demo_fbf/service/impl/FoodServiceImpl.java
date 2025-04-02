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
    private FoodDto foodDto;
    private List<Food> foodList;
    private Page<Food> foodPage;
    private List<FoodDto> foodDtos = new ArrayList<>();

    @Override
    public Page<FoodDto> findByContainName(PageRequest pageRequest,String namePattern) {
        foodPage = foodRepository.findByNameContainingIgnoreCase(pageRequest,namePattern);
        foodList = foodPage.getContent();
        for(int i = 0 ; i < foodList.size() ; i++){
            foodDto = foodMapper.toDto(foodList.get(i));
            foodDtos.add(foodDto);
        }
        return new PageImpl<>(foodDtos, foodPage.getPageable(), foodPage.getTotalElements()+1);
    }
}
