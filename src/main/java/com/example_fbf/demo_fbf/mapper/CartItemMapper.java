package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.CartItemDisplayDto;
import com.example_fbf.demo_fbf.dto.CartItemDto;
import com.example_fbf.demo_fbf.entity.CartItem;
import com.example_fbf.demo_fbf.entity.Food;
import com.example_fbf.demo_fbf.entity.FoodSize;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FoodSizeMapper.class})
public interface CartItemMapper {
    @Mapping(source = "cart.id", target = "cartId")
    CartItemDto toDto(CartItem cartItem);

    @Mapping(source = "cartId", target = "cart.id")
    CartItem toEntity(CartItemDto cartItemDto);

    default CartItemDisplayDto toDisplayDto(CartItem item) {
        FoodSize fs = item.getFoodSize();
        Food food = fs.getFood(); // assuming getFood() is available

        return new CartItemDisplayDto(
                item.getId(),
                food.getName(),
                food.getImageUrl(), // Make sure Food entity has this field
                fs.getSize(),
                item.getPrice(),
                item.getDiscountPercentage(),
                item.getQuantity()
        );
    }
}
