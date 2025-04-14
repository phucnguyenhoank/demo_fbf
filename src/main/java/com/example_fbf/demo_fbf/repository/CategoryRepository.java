package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    public List<Category> findAll();

}
