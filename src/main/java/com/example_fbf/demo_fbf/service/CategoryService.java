package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.dto.CategoryDto;
import com.example_fbf.demo_fbf.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface CategoryService {
    List<CategoryDto> getAllCategory();
}
