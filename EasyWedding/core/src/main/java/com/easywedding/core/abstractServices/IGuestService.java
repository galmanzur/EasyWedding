package com.easywedding.core.abstractServices;

import com.easywedding.core.entities.Guest;

import java.util.List;

public interface IGuestService {
    Guest createGuest(Guest guest);
    Guest updateGuest(Guest guest);
    void delete(Long id);
    Guest getById(Long id);
    List<Guest> getByWeddingId(Long weddingId);
    List<Guest> searchByName(String name);
    List<Guest> getByTableNumber(Long tableNumber);
    long countByTableNumber(Long tableNumber);
}
