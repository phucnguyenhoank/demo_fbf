package com.example_fbf.demo_fbf.service;

import com.example_fbf.demo_fbf.dto.FbfOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public interface FbfOrderService {
    Page<FbfOrderDto> getAllOrderByOrderId(PageRequest pageRequest, Long id);
}
