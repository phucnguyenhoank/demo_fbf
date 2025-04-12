package com.example_fbf.demo_fbf.controller;

import com.example_fbf.demo_fbf.dto.FbfOrderDto;
import com.example_fbf.demo_fbf.entity.FbfOrder;
import com.example_fbf.demo_fbf.service.impl.FbfOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/fbforder")
public class FbfOrderController {
    @Autowired
    private FbfOrderServiceImpl fbfOrderService;

    @GetMapping
    private Page<FbfOrderDto> getFbfOrderByUserId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "userId,asc") String sort,
            @RequestParam(defaultValue = "") Long userId
    )
    {
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));
        return fbfOrderService.getAllOrderByOrderId(pageRequest, userId);
    }
}
