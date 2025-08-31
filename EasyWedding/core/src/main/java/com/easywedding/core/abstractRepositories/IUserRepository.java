package com.easywedding.core.abstractRepositories;

import com.easywedding.core.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void deleteById(Long id);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
    List<User> findByWeddingId(Long weddingId);
}
