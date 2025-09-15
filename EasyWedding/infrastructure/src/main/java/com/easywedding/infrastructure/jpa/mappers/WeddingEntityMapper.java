package com.easywedding.infrastructure.jpa.mappers;

import com.easywedding.core.entities.Wedding;
import com.easywedding.infrastructure.jpa.entities.WeddingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WeddingEntityMapper {

    // Core -> Entity
    // Ignore collections, they will be handled separately in services/adapters.
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "guests", ignore = true)
    @Mapping(target = "seatingTables", ignore = true)
    WeddingEntity toEntity(Wedding wedding);

    // Entity -> Core
    // Extract only the wedding.id and ignore collections (map them manually if needed).
    Wedding toDomain(WeddingEntity entity);
}
