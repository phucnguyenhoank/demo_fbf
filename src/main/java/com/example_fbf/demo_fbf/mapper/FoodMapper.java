package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.FoodDto;
import com.example_fbf.demo_fbf.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FoodSizeMapper.class)
public interface FoodMapper {
    @Mapping(source = "category.id", target = "categoryId")
    FoodDto toDto(Food food);

    @Mapping(source = "categoryId", target = "category.id")
    Food toEntity(FoodDto foodDto);
}
