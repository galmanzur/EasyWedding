package com.easywedding.infrastructure.jpa.repositories;

import com.easywedding.infrastructure.jpa.entities.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<GuestEntity, Long> {
    List<GuestEntity> findByWeddingId(Long weddingId);
    List<GuestEntity> findByNameContainingIgnoreCase(String name);
    List<GuestEntity> findByTable_Number(Long number);
    long countByTable_Number(Long number);
}
