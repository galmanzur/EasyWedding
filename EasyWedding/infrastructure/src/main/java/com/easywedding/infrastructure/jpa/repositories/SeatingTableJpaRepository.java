package com.easywedding.infrastructure.jpa.repositories;

import com.easywedding.infrastructure.jpa.entities.SeatingTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatingTableJpaRepository extends JpaRepository<SeatingTableEntity, Long> {
    List<SeatingTableEntity> findByWeddingId(Long weddingId);
}
