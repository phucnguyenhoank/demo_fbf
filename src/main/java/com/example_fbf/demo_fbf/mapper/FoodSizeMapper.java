package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.FoodSizeDto;
import com.example_fbf.demo_fbf.entity.FoodSize;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoodSizeMapper {
    @Mapping(source = "food.id", target = "foodId")
    FoodSizeDto toDto(FoodSize foodSize);

    @Mapping(source = "foodId", target = "food.id")
    FoodSize toEntity(FoodSizeDto foodSizeDto);
}
