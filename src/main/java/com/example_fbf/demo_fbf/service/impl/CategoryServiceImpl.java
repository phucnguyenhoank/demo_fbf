package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.dto.CategoryDto;
import com.example_fbf.demo_fbf.entity.Category;
import com.example_fbf.demo_fbf.mapper.CategoryMapper;
import com.example_fbf.demo_fbf.repository.CategoryRepository;
import com.example_fbf.demo_fbf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        CategoryDto categoryDto = new CategoryDto();
        for (int i = 0; i < categories.size(); i++){
            categoryDto = categoryMapper.toDto(categories.get(i));
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }
}
