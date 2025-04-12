package com.example_fbf.demo_fbf.mapper;

import com.example_fbf.demo_fbf.dto.FbfOrderDto;
import com.example_fbf.demo_fbf.entity.FbfOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, DiscountCodeMapper.class})
public interface FbfOrderMapper {

    @Mapping(source = "fbfUser.id", target = "fbfUserId")
    FbfOrderDto toDto(FbfOrder fbfOrder);

    @Mapping(target = "fbfUser", ignore = true) // Let the service set it using userId
    FbfOrder toEntity(FbfOrderDto fbfOrderDto);
}

