package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.DiscountCodeDto;
import com.example_fbf.demo_fbf.entity.DiscountCode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiscountCodeMapper {
    DiscountCodeDto toDto(DiscountCode discountCode);
    DiscountCode toEntity(DiscountCodeDto discountCodeDto);
}
