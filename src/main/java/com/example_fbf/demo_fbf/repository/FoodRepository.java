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

    Page<Food> findByNameContainingIgnoreCase(PageRequest pageRequest, String namePattern);
    Optional<Food> findById(Long id);
    Page<Food> findByCategoryId(PageRequest pageRequest, Long id);
    @Query("SELECT f FROM Food f WHERE EXISTS (SELECT s FROM FoodSize s WHERE s.food = f AND s.price BETWEEN :min AND :max)")
    Page<Food> findFoodsBySizePriceBetween(Pageable pageable, @Param("min") Double min, @Param("max") Double max);

    @Query("""
    SELECT f FROM Food f 
        WHERE EXISTS ( 
            SELECT s FROM FoodSize s 
            WHERE s.food = f AND s.price BETWEEN :min AND :max 
        )
        AND (:name = '' OR LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%'))) 
        AND (:categoryId = 0 OR f.category.id = :categoryId) 
    """)
    Page<Food> searchFoods(Pageable pageable,
                           @Param("min") Double min,
                           @Param("max") Double max,
                           @Param("name") String name,
                           @Param("categoryId") Long categoryId);


}
