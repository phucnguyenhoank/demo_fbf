package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.dto.FoodSalesDto;
import com.example_fbf.demo_fbf.entity.OrderItem;
import com.example_fbf.demo_fbf.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT new com.example_fbf.demo_fbf.dto.FoodSalesDto(f.id, f.name, SUM(oi.quantity)) " +
            "FROM OrderItem oi " +
            "JOIN oi.order o " +
            "JOIN oi.cartItem ci " +
            "JOIN ci.food f " +
            "WHERE o.status = :paidStatus " +
            "GROUP BY f.id, f.name " +
            "ORDER BY SUM(oi.quantity) DESC")
    List<FoodSalesDto> findTopSoldFoods(@Param("paidStatus") OrderStatus paidStatus);
}
