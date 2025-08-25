package com.easywedding.infrastructure.jpa.mappers;

import com.easywedding.core.entities.Guest;
import com.easywedding.infrastructure.jpa.entities.GuestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestEntityMapper {

    GuestEntity toEntity(Guest guest);

    Guest toDomain(GuestEntity entity);
}
