package com.example_fbf.demo_fbf;

import com.example_fbf.demo_fbf.dto.CategoryDto;
import com.example_fbf.demo_fbf.dto.FoodDto;
import com.example_fbf.demo_fbf.entity.Category;
import com.example_fbf.demo_fbf.entity.Food;
import com.example_fbf.demo_fbf.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void testToDto() {
        // Setup data
        Category category = new Category();
        category.setId(1L);
        category.setName("Drinks");

        Food food = new Food();
        food.setId(10L);
        food.setName("Coca Cola");
        food.setDescription("Cold drink");
        food.setImageUrl("coke.png");
        food.setCategory(category); // set ngược lại cho đúng quan hệ

        category.setFoodList(List.of(food));

        // Act
        CategoryDto dto = categoryMapper.toDto(category);

        // Assert
        assertNotNull(dto);
        assertEquals(category.getId(), dto.getId());
        assertEquals(category.getName(), dto.getName());
        assertNotNull(dto.getFoodList());
        assertEquals(1, dto.getFoodList().size());
        assertEquals("Coca Cola", dto.getFoodList().get(0).getName());
    }

    @Test
    void testToEntity() {
        FoodDto foodDto = new FoodDto();
        foodDto.setId(20L);
        foodDto.setName("Pepsi");
        foodDto.setDescription("Another drink");
        foodDto.setImageUrl("pepsi.png");
        foodDto.setCategoryId(2L);

        CategoryDto dto = new CategoryDto();
        dto.setId(2L);
        dto.setName("Beverages");
        dto.setFoodList(List.of(foodDto));

        Category entity = categoryMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertNotNull(entity.getFoodList());
        assertEquals(1, entity.getFoodList().size());
        assertEquals("Pepsi", entity.getFoodList().get(0).getName());
        assertEquals(2L, entity.getFoodList().get(0).getCategory().getId());
    }
}
