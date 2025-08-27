package com.easywedding.webapi.mappers;

import com.easywedding.core.entities.Wedding;
import com.easywedding.webapi.dtos.WeddingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeddingDtoMapper {
    WeddingDto toDto(Wedding wedding);
    Wedding toDomain(WeddingDto dto);
}
