package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.OrderItemDto;
import com.example_fbf.demo_fbf.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FoodSizeMapper.class})
public interface OrderItemMapper {

    @Mapping(source = "fbfOrder.id", target = "fbfOrderId")
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(source = "fbfOrderId", target = "fbfOrder.id")
    OrderItem toEntity(OrderItemDto orderItemDto);
}
