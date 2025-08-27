package com.easywedding.infrastructure.jpa.mappers;

import com.easywedding.core.entities.Wedding;
import com.easywedding.infrastructure.jpa.entities.WeddingEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeddingEntityMapper {
    Wedding toDomain(WeddingEntity entity);
    WeddingEntity toEntity(Wedding domain);
}
