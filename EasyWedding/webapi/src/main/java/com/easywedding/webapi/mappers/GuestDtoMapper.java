package com.easywedding.webapi.mappers;
import com.easywedding.webapi.dtos.GuestDto;
import com.easywedding.core.entities.Guest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestDtoMapper {
    GuestDto toDto(Guest guest);
    Guest toDomain(GuestDto guestDto);
}
