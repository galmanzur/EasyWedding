package com.easywedding.core.abstractRepositories;

import com.easywedding.core.entities.Guest;

import java.util.List;

public interface IGuestRepository {
    Guest save(Guest guest);
    void delete(Long id);
    Guest getById(Long id);
    List<Guest> getByWeddingId(Long weddingId);
    List<Guest> searchByName(String name);
    List<Guest> getByTableNumber(Long tableNumber);
    long countByTableNumber(Long tableNumber);
}
