package com.example_fbf.demo_fbf.repository;

import com.example_fbf.demo_fbf.entity.FbfOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FbfOrderRepository extends JpaRepository<FbfOrder, Long> {
    public List<FbfOrder> findAll();
    public Optional<FbfOrder> findById(Long orderId);
    public Page<FbfOrder> findByFbfUser_Id(PageRequest pageRequest, Long id);
}
