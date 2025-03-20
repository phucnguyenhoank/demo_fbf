package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.CartItemDto;
import com.example_fbf.demo_fbf.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FoodSizeMapper.class})
public interface CartItemMapper {
    CartItemDto toDto(CartItem cartItem);
    CartItem toEntity(CartItemDto cartItemDto);
}
