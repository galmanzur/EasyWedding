package com.easywedding.infrastructure.jpa.adapters;

import com.easywedding.core.abstractRepositories.IWeddingRepository;
import com.easywedding.core.entities.Wedding;
import com.easywedding.infrastructure.jpa.entities.WeddingEntity;
import com.easywedding.infrastructure.jpa.mappers.WeddingEntityMapper;
import com.easywedding.infrastructure.jpa.repositories.WeddingRepository;
import org.springframework.stereotype.Repository;

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
        return mapper.toDomain(repo.save(mapper.toEntity(wedding)));
    }

    @Override
    public Wedding findById(Long id) {
        return repo.findById(id).map(mapper::toDomain).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
