package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.dto.OrderItemDetailDto;
import com.example_fbf.demo_fbf.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT new com.example_fbf.demo_fbf.dto.OrderItemDetailDto(" +
            "oi.id, fs.size, oi.discountedPrice, oi.discountPercentage, " +
            "oi.quantity, f.name, f.imageUrl, o.createdAt) " +
            "FROM OrderItem oi " +
            "JOIN oi.foodSize fs " +
            "JOIN fs.food f " +
            "JOIN oi.fbfOrder o " +
            "WHERE o.id = :orderId")
    List<OrderItemDetailDto> findDetailsByOrderId(@Param("orderId") Long orderId);
}
