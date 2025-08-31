package com.easywedding.webapi.mappers;

import com.easywedding.core.entities.User;
import com.easywedding.webapi.dtos.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    User toDomain(UserDto dto);
    UserDto toDto(User domain);
}
