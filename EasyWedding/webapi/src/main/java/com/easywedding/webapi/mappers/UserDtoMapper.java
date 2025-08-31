package com.easywedding.webapi.mappers;

import com.easywedding.core.entities.User;
import com.easywedding.webapi.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public User toDomain(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setWeddingId(dto.getWeddingId());
        user.setPermissionLevel(dto.getPermissionLevel());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setWeddingId(user.getWeddingId());
        dto.setPermissionLevel(user.getPermissionLevel());
        return dto;
    }
}
