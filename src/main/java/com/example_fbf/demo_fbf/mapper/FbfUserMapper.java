package com.example_fbf.demo_fbf.mapper;
import com.example_fbf.demo_fbf.dto.FbfUserDto;
import com.example_fbf.demo_fbf.entity.FbfRole;
import com.example_fbf.demo_fbf.entity.FbfUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FbfUserMapper {
    @Mapping(source = "fbfRole", target = "fbfRole")
    FbfUserDto toDto(FbfUser fbfUser);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(source = "fbfRole", target = "fbfRole")
    FbfUser toEntity(FbfUserDto fbfUserDto);

    // Custom mapping from enum to String
    default String map(FbfRole role) {
        return role == null ? null : role.name();
    }

    // Custom mapping from String to enum
    default FbfRole map(String role) {
        return role == null ? null : FbfRole.valueOf(role);
    }
}
