package com.easywedding.infrastructure.jpa.mappers;

import com.easywedding.core.entities.SeatingTable;
import com.easywedding.infrastructure.jpa.entities.SeatingTableEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeatingTableMapper {
    SeatingTableEntity toEntity(SeatingTable table);
    SeatingTable toDomain(SeatingTableEntity entity);
}
