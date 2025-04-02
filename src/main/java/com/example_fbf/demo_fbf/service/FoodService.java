package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.dto.FoodDto;
import com.example_fbf.demo_fbf.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface FoodService {
    Page<FoodDto> findByContainName(PageRequest pageRequest, String namePattern);
}
