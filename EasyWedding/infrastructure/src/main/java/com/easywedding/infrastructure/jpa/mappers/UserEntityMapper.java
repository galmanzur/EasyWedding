package com.easywedding.infrastructure.jpa.mappers;

import com.easywedding.core.entities.User;
import com.easywedding.infrastructure.jpa.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toEntity(User domain);
    User toDomain(UserEntity entity);
}
