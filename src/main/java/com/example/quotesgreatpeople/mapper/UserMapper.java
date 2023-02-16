package com.example.quotesgreatpeople.mapper;

import com.example.quotesgreatpeople.dto.UserDto;
import com.example.quotesgreatpeople.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDto toUserDto(UserEntity entity);

    UserEntity toUserEntity(UserDto dto);
}

