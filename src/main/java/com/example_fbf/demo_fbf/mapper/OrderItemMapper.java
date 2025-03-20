package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.OrderItemDto;
import com.example_fbf.demo_fbf.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FoodSizeMapper.class})
public interface OrderItemMapper {
    OrderItemDto toDto(OrderItem orderItem);
    OrderItem toEntity(OrderItemDto orderItemDto);
}
