package com.easywedding.infrastructure.jpa.adapters;

import com.easywedding.core.abstractRepositories.ISeatingTableRepository;
import com.easywedding.core.entities.SeatingTable;
import com.easywedding.infrastructure.jpa.entities.SeatingTableEntity;
import com.easywedding.infrastructure.jpa.mappers.SeatingTableMapper;
import com.easywedding.infrastructure.jpa.repositories.SeatingTableJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SeatingTableRepositoryAdapter implements ISeatingTableRepository {

    private final SeatingTableJpaRepository jpaRepository;
    private final SeatingTableMapper mapper;

    public SeatingTableRepositoryAdapter(SeatingTableJpaRepository jpaRepository, SeatingTableMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SeatingTable save(SeatingTable table) {
        SeatingTableEntity saved = jpaRepository.save(mapper.toEntity(table));
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
