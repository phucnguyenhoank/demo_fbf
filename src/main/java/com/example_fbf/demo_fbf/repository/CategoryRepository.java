package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
