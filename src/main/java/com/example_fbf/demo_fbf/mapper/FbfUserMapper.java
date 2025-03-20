package com.example_fbf.demo_fbf.mapper;
import com.example_fbf.demo_fbf.dto.FbfUserDto;
import com.example_fbf.demo_fbf.entity.FbfUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FbfUserMapper {
    FbfUserDto toDto(FbfUser fbfUser);
    FbfUser toEntity(FbfUserDto fbfUserDto);
}
