package com.easywedding.webapi.mappers;

import com.easywedding.core.entities.SeatingTable;
import com.easywedding.webapi.dtos.SeatingTableDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeatingTableDtoMapper {
    SeatingTableDto toDto(SeatingTable seatingTable);
    SeatingTable toDomain(SeatingTableDto seatingTableDto);
}
