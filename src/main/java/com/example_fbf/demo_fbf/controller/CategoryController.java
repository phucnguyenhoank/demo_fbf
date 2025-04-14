package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.CategoryDto;
import com.example_fbf.demo_fbf.entity.Category;
import com.example_fbf.demo_fbf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategory();
    }
}
