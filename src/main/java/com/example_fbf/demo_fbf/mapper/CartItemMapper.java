package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.CartItemDto;
import com.example_fbf.demo_fbf.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FoodSizeMapper.class})
public interface CartItemMapper {
    @Mapping(source = "cart.id", target = "cartId")
    CartItemDto toDto(CartItem cartItem);

    @Mapping(source = "cartId", target = "cart.id")
    CartItem toEntity(CartItemDto cartItemDto);
}
