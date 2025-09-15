package com.easywedding.infrastructure.jpa.mappers;

import com.easywedding.core.entities.User;
import com.easywedding.infrastructure.jpa.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    // Core -> Entity
    // Ignore wedding because in core we only store weddingId.
    // The actual WeddingEntity will be set in the adapter/service.
    @Mapping(target = "wedding", ignore = true)
    UserEntity toEntity(User user);

    // Entity -> Core
    // Extract wedding.id into weddingId for the core model.
    @Mapping(target = "weddingId", source = "wedding.id")
    User toDomain(UserEntity entity);
}
