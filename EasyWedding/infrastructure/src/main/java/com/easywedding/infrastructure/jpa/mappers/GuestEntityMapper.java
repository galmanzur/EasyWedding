package com.easywedding.infrastructure.jpa.mappers;

import com.easywedding.core.entities.Guest;
import com.easywedding.infrastructure.jpa.entities.GuestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuestEntityMapper {

    // Core -> Entity
    // Ignore wedding and table, these will be set in the adapter/service.
    @Mapping(target = "wedding", ignore = true)
    @Mapping(target = "table", ignore = true)
    GuestEntity toEntity(Guest guest);

    // Entity -> Core
    // Map nested IDs back to simple Longs in the core entity.
    @Mapping(target = "weddingId", source = "wedding.id")
    @Mapping(target = "tableNumber", source = "table.number")
    Guest toDomain(GuestEntity entity);
}
