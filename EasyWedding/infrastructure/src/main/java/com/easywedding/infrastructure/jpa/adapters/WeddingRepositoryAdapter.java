package com.easywedding.infrastructure.jpa.adapters;

import com.easywedding.core.abstractRepositories.IWeddingRepository;
import com.easywedding.core.entities.Wedding;
import com.easywedding.infrastructure.jpa.entities.WeddingEntity;
import com.easywedding.infrastructure.jpa.mappers.WeddingEntityMapper;
import com.easywedding.infrastructure.jpa.repositories.WeddingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WeddingRepositoryAdapter implements IWeddingRepository {

    private final WeddingRepository repo;
    private final WeddingEntityMapper mapper;

    public WeddingRepositoryAdapter(WeddingRepository repo, WeddingEntityMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Wedding save(Wedding wedding) {
        WeddingEntity entity = mapper.toEntity(wedding);

        // When saving a wedding, all foreign key relations (guests, users, tables)
        // are automatically handled by cascade settings in WeddingEntity.
        WeddingEntity saved = repo.save(entity);

        return mapper.toDomain(saved);
    }

    @Override
    public Wedding findById(Long id) {
        return repo.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    public List<Wedding> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
