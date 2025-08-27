package com.easywedding.infrastructure.services;

import com.easywedding.core.abstractRepositories.IWeddingRepository;
import com.easywedding.core.abstractServices.IWeddingService;
import com.easywedding.core.entities.Wedding;
import org.springframework.stereotype.Service;

@Service
public class WeddingServiceImpl implements IWeddingService {

    private final IWeddingRepository repository;

    public WeddingServiceImpl(IWeddingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Wedding create(Wedding wedding) {
        return repository.save(wedding);
    }

    @Override
    public Wedding getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
