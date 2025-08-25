package com.easywedding.core.abstractRepositories;

import com.easywedding.core.entities.SeatingTable;

import java.util.List;

public interface ISeatingTableRepository {
    SeatingTable save(SeatingTable table);
    void delete(Long number);
    List<SeatingTable> findByWeddingId(Long weddingId);
}
