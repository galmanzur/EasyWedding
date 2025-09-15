package com.easywedding.infrastructure.jpa.mappers;

import com.easywedding.core.entities.Guest;
import com.easywedding.infrastructure.jpa.entities.GuestEntity;
import com.easywedding.infrastructure.jpa.entities.SeatingTableEntity;
import org.springframework.stereotype.Component;

@Component
public class GuestEntityMapper {

    public GuestEntity toEntity(Guest domain) {
        GuestEntity entity = new GuestEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setPhoneNumber(domain.getPhoneNumber());
        entity.setNotes(domain.getNotes());
        entity.setStatus(domain.getStatus());
        entity.setWeddingId(domain.getWeddingId());

        SeatingTableEntity table = new SeatingTableEntity();
        table.setNumber(domain.getTableNumber());
        entity.setTable(table);

        return entity;
    }

    public Guest toDomain(GuestEntity entity) {
        Guest domain = new Guest();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setPhoneNumber(entity.getPhoneNumber());
        domain.setNotes(entity.getNotes());
        domain.setStatus(entity.getStatus());
        domain.setWeddingId(entity.getWeddingId());

        if (entity.getTable() != null) {
            domain.setTableNumber(entity.getTable().getNumber());
        }

        return domain;
    }
}
