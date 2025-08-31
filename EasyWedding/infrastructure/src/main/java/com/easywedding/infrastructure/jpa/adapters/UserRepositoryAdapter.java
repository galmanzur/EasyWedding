package com.easywedding.infrastructure.jpa.adapters;

import com.easywedding.core.abstractRepositories.IUserRepository;
import com.easywedding.core.entities.User;
import com.easywedding.infrastructure.jpa.entities.UserEntity;
import com.easywedding.infrastructure.jpa.mappers.UserEntityMapper;
import com.easywedding.infrastructure.jpa.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryAdapter implements IUserRepository {

    private final UserRepository jpaRepository;
    private final UserEntityMapper mapper;

    public UserRepositoryAdapter(UserRepository jpaRepository, UserEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return jpaRepository.findByUsernameAndPassword(username, password)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public List<User> findByWeddingId(Long weddingId) {
        return jpaRepository.findByWeddingId(weddingId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
