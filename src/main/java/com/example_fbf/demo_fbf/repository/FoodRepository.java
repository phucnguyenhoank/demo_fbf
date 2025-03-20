package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
