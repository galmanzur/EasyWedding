package com.easywedding.infrastructure.services;

import com.easywedding.core.abstractRepositories.ISeatingTableRepository;
import com.easywedding.core.abstractServices.ISeatingTableService;
import com.easywedding.core.entities.SeatingTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingTableServiceImpl implements ISeatingTableService {

    private final ISeatingTableRepository repository;

    public SeatingTableServiceImpl(ISeatingTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public SeatingTable create(SeatingTable table) {
        return repository.save(table);
    }

    @Override
    public List<SeatingTable> getByWeddingId(Long weddingId) {
        return repository.findByWeddingId(weddingId);
    }

    @Override
    public void delete(Long number) {
        repository.delete(number);
    }
}
