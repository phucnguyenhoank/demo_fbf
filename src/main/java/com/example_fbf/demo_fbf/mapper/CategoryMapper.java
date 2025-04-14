package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.CategoryDto;
import com.example_fbf.demo_fbf.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = FoodMapper.class)
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    @Mapping(source = "foodList", target = "foodList")
    Category toEntity(CategoryDto categoryDto);
}
