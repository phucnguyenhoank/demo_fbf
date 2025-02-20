package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.FoodSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodSizeRepository extends JpaRepository<FoodSize, Long> {
}
