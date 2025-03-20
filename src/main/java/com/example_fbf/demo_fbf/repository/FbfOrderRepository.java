package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.FbfOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FbfOrderRepository extends JpaRepository<FbfOrder, Long> {
}
