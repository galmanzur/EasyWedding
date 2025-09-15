package com.easywedding.infrastructure.jpa.adapters;

import com.easywedding.core.abstractRepositories.ISeatingTableRepository;
import com.easywedding.core.entities.SeatingTable;
import com.easywedding.infrastructure.jpa.entities.SeatingTableEntity;
import com.easywedding.infrastructure.jpa.entities.WeddingEntity;
import com.easywedding.infrastructure.jpa.mappers.SeatingTableMapper;
import com.easywedding.infrastructure.jpa.repositories.SeatingTableRepository;
import com.easywedding.infrastructure.jpa.repositories.WeddingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SeatingTableRepositoryAdapter implements ISeatingTableRepository {

    private final SeatingTableRepository jpaRepository;
    private final WeddingRepository weddingRepository;
    private final SeatingTableMapper mapper;

    public SeatingTableRepositoryAdapter(
            SeatingTableRepository jpaRepository,
            WeddingRepository weddingRepository,
            SeatingTableMapper mapper
    ) {
        this.jpaRepository = jpaRepository;
        this.weddingRepository = weddingRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public SeatingTable save(SeatingTable table) {
        SeatingTableEntity entity = mapper.toEntity(table);

        // Handle foreign key: every table must belong to a wedding
        if (table.getWeddingId() == null) {
            throw new RuntimeException("Seating table must be associated with a wedding");
        } else {
            WeddingEntity wedding = weddingRepository.findById(table.getWeddingId())
                    .orElseThrow(() -> new RuntimeException(
                            "Wedding not found with ID: " + table.getWeddingId()
                    ));
            entity.setWedding(wedding);
        }

        SeatingTableEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<SeatingTable> findByWeddingId(Long weddingId) {
        return jpaRepository.findByWeddingId(weddingId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
