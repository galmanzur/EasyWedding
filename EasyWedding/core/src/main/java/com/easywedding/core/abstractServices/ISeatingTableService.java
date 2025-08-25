package com.easywedding.core.abstractServices;

import com.easywedding.core.entities.SeatingTable;

import java.util.List;

public interface ISeatingTableService {
    SeatingTable create(SeatingTable table);
    List<SeatingTable> getByWeddingId(Long weddingId);
    void delete(Long id);
}
