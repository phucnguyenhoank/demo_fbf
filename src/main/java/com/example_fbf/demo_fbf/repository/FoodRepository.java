package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    public Page<Food> findByNameContainingIgnoreCase(PageRequest pageRequest, String namePattern);
    public Optional<Food> findById(Long id);
    public Page<Food> findByCategoryId(PageRequest pageRequest, Long id);
    @Query("SELECT f FROM Food f JOIN f.sizes s WHERE s.price BETWEEN :min AND :max")
    public Page<Food> findFoodsBySizePriceBetween(Pageable pageable, @Param("min") Double min, @Param("max") Double max);


}
