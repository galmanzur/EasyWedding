package com.easywedding.infrastructure.jpa.adapters;

import com.easywedding.core.abstractRepositories.IGuestRepository;
import com.easywedding.core.entities.Guest;
import com.easywedding.infrastructure.jpa.entities.GuestEntity;
import com.easywedding.infrastructure.jpa.entities.SeatingTableEntity;
import com.easywedding.infrastructure.jpa.entities.WeddingEntity;
import com.easywedding.infrastructure.jpa.mappers.GuestEntityMapper;
import com.easywedding.infrastructure.jpa.repositories.GuestRepository;
import com.easywedding.infrastructure.jpa.repositories.SeatingTableRepository;
import com.easywedding.infrastructure.jpa.repositories.WeddingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GuestRepositoryAdapter implements IGuestRepository {

    private final GuestRepository guestRepository;
    private final SeatingTableRepository seatingTableRepository;
    private final WeddingRepository weddingRepository;
    private final GuestEntityMapper mapper;

    public GuestRepositoryAdapter(
            GuestRepository guestRepository,
            SeatingTableRepository seatingTableRepository,
            WeddingRepository weddingRepository,
            GuestEntityMapper mapper
    ) {
        this.guestRepository = guestRepository;
        this.seatingTableRepository = seatingTableRepository;
        this.weddingRepository = weddingRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Guest save(Guest guest) {
        GuestEntity entity = mapper.toEntity(guest);

        // Handle nullable table (can be null or an existing table)
        if (guest.getTableNumber() == null) {
            entity.setTable(null);
        } else {
            SeatingTableEntity table = seatingTableRepository.findById(guest.getTableNumber())
                    .orElseThrow(() -> new RuntimeException(
                            "Seating table not found with number: " + guest.getTableNumber()
                    ));
            entity.setTable(table);
        }

        // Handle wedding (must not be null, must exist in DB)
        if (guest.getWeddingId() == null) {
            throw new RuntimeException("Guest must be associated with a wedding");
        } else {
            WeddingEntity wedding = weddingRepository.findById(guest.getWeddingId())
                    .orElseThrow(() -> new RuntimeException(
                            "Wedding not found with ID: " + guest.getWeddingId()
                    ));
            entity.setWedding(wedding);
        }

        GuestEntity saved = guestRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void delete(Long id) {
        guestRepository.deleteById(id);
    }

    @Override
    public Guest getById(Long id) {
        return guestRepository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + id));
    }

    @Override
    public List<Guest> getByWeddingId(Long weddingId) {
        return guestRepository.findByWeddingId(weddingId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Guest> searchByName(String name) {
        return guestRepository.findByNameContainingIgnoreCase(name).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Guest> getByTableNumber(Long tableNumber) {
        return guestRepository.findByTable_Number(tableNumber).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long countByTableNumber(Long tableNumber) {
        return guestRepository.countByTable_Number(tableNumber);
    }
}
