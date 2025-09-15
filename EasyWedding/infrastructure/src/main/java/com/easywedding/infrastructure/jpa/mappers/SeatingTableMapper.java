package com.easywedding.infrastructure.jpa.mappers;

import com.easywedding.core.entities.SeatingTable;
import com.easywedding.infrastructure.jpa.entities.SeatingTableEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatingTableMapper {

    // Core -> Entity
    // Ignore wedding because we only map the ID at core level.
    // The actual WeddingEntity should be attached in the service/adapter layer.
    @Mapping(target = "wedding", ignore = true)
    SeatingTableEntity toEntity(SeatingTable table);

    // Entity -> Core
    // Extract the wedding.id and set it into weddingId in the core object.
    @Mapping(target = "weddingId", source = "wedding.id")
    SeatingTable toDomain(SeatingTableEntity entity);
}
