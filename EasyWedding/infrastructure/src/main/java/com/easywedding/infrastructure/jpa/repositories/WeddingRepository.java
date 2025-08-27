package com.easywedding.infrastructure.jpa.repositories;

import com.easywedding.infrastructure.jpa.entities.WeddingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeddingRepository extends JpaRepository<WeddingEntity, Long> {
}
