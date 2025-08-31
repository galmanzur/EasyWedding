package com.easywedding.infrastructure.jpa.repositories;

import com.easywedding.infrastructure.jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
    List<UserEntity> findByWeddingId(Long weddingId);
}
