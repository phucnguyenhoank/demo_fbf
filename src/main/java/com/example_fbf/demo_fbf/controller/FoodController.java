package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.FoodDto;
import com.example_fbf.demo_fbf.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping
    public Page<FoodDto> findFoodByNameKeywords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name,asc") String sort,
            @RequestParam(defaultValue = "") String keyword
    ){
        keyword = "a";
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));

        return foodService.findByContainName(pageRequest, keyword);
    }
}
