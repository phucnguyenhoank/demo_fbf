package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.Food;
import jdk.jfr.Registered;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    public Page<Food> findByNameContainingIgnoreCase(PageRequest pageRequest, String namePattern);
}
