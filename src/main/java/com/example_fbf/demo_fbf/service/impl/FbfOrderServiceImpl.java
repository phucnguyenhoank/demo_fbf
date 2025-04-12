package com.example_fbf.demo_fbf.service.impl;

import com.example_fbf.demo_fbf.dto.FbfOrderDto;
import com.example_fbf.demo_fbf.entity.FbfOrder;
import com.example_fbf.demo_fbf.mapper.FbfOrderMapper;
import com.example_fbf.demo_fbf.repository.FbfOrderRepository;
import com.example_fbf.demo_fbf.service.FbfOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FbfOrderServiceImpl implements FbfOrderService {
    @Autowired
    private FbfOrderRepository fbfOrderRepository;
    private List<FbfOrder> fbfOrder;
    @Autowired
    private FbfOrderMapper fbfOrderMapper;
    private List<FbfOrderDto> fbfOrderDtoList = new ArrayList<>();

    @Override
    public Page<FbfOrderDto> getAllOrderByOrderId(PageRequest pageRequest, Long id){
        Page<FbfOrder> fbfOrderPage = fbfOrderRepository.findByUserId(pageRequest,id);
        if(!fbfOrderPage.isEmpty()) {
            fbfOrder = fbfOrderPage.getContent();
            FbfOrderDto fbfOrderDto = new FbfOrderDto();
            for (FbfOrder item : fbfOrder) {
                fbfOrderDto = fbfOrderMapper.toDto(item);
                fbfOrderDtoList.add(fbfOrderDto);
            }
        }
        return new PageImpl<>(fbfOrderDtoList, fbfOrderPage.getPageable(), fbfOrderPage.getTotalElements()+1);
    }
}
