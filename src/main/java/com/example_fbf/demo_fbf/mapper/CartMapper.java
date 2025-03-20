package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.CartDto;
import com.example_fbf.demo_fbf.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {
    @Mapping(source = "user.id", target = "userId")
    CartDto toDto(Cart cart);

    @Mapping(target = "user", ignore = true)
    Cart toEntity(CartDto cartDto);
}