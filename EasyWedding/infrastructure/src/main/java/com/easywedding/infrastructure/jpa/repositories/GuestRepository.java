package com.easywedding.infrastructure.jpa.repositories;

import com.easywedding.infrastructure.jpa.entities.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;

public interface GuestRepository extends JpaRepository<GuestEntity, Long> {
    // Optional: add query methods like findByWeddingID(Long id)
}
