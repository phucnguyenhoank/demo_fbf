package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.FoodDto;
import com.example_fbf.demo_fbf.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FoodSizeMapper.class)
public interface FoodMapper {
    FoodDto toDto(Food food);
    Food toEntity(FoodDto foodDto);
}
