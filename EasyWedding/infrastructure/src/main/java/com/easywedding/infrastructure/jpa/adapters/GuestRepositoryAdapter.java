package com.easywedding.infrastructure.jpa.adapters;

import com.easywedding.core.abstractRepositories.IGuestRepository;
import com.easywedding.core.entities.Guest;
import com.easywedding.infrastructure.jpa.entities.GuestEntity;
import com.easywedding.infrastructure.jpa.repositories.GuestRepository;
import com.easywedding.infrastructure.jpa.mappers.GuestEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GuestRepositoryAdapter implements IGuestRepository {

    private final GuestRepository jpaRepository;
    private final GuestEntityMapper mapper;

    public GuestRepositoryAdapter(GuestRepository jpaRepository, GuestEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Guest save(Guest guest) {
        GuestEntity entity = mapper.toEntity(guest);
        GuestEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    /*@Override
    public List<Guest> findByWeddingId(Long weddingId) {
        return jpaRepository.findByWeddingId(weddingId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }*/

    @Override
    public void delete(Long guestId) {
        jpaRepository.deleteById(guestId);
    }
}
