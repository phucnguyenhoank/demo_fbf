package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.CartDto;
import com.example_fbf.demo_fbf.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {
    @Mapping(source = "fbfUser.id", target = "fbfUserId")
    CartDto toDto(Cart cart);

    @Mapping(source = "fbfUserId", target = "fbfUser.id")
    Cart toEntity(CartDto cartDto);
}