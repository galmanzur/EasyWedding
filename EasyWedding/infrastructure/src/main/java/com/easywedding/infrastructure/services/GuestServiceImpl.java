package com.easywedding.infrastructure.services;

import com.easywedding.core.abstractRepositories.IGuestRepository;
import com.easywedding.core.abstractServices.IGuestService;
import com.easywedding.core.entities.Guest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements IGuestService {

    private final IGuestRepository repository;

    public GuestServiceImpl(IGuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Guest createGuest(Guest guest) {
        return repository.save(guest);
    }

    @Override
    public Guest updateGuest(Guest guest) {
        return repository.save(guest);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Guest getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<Guest> getByWeddingId(Long weddingId) {
        return repository.getByWeddingId(weddingId);
    }

    @Override
    public List<Guest> searchByName(String name) {
        return repository.searchByName(name);
    }

    @Override
    public List<Guest> getByTableNumber(Long tableNumber) {
        return repository.getByTableNumber(tableNumber);
    }

    @Override
    public long countByTableNumber(Long tableNumber) {
        return repository.countByTableNumber(tableNumber);
    }
}
