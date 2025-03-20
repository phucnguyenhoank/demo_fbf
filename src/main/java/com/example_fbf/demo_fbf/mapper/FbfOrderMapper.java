package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.FbfOrderDto;
import com.example_fbf.demo_fbf.entity.FbfOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, DiscountCodeMapper.class})
public interface FbfOrderMapper {
    @Mapping(source = "user.id", target = "userId")
    FbfOrderDto toDto(FbfOrder fbfOrder);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "discountCode", ignore = true)
    FbfOrder toEntity(FbfOrderDto fbfOrderDto);
}
