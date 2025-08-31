package com.easywedding.infrastructure.jpa.mappers;

import com.easywedding.core.entities.User;
import com.easywedding.infrastructure.jpa.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    public User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setWeddingId(entity.getWeddingId());
        user.setPermissionLevel(entity.getPermissionLevel());
        return user;
    }

    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setWeddingId(user.getWeddingId());
        entity.setPermissionLevel(user.getPermissionLevel());
        return entity;
    }
}
