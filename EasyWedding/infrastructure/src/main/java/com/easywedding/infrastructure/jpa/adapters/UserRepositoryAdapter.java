package com.easywedding.infrastructure.jpa.adapters;

import com.easywedding.core.entities.User;
import com.easywedding.core.abstractRepositories.IUserRepository;
import com.easywedding.infrastructure.jpa.entities.UserEntity;
import com.easywedding.infrastructure.jpa.entities.WeddingEntity;
import com.easywedding.infrastructure.jpa.mappers.UserEntityMapper;
import com.easywedding.infrastructure.jpa.repositories.UserRepository;
import com.easywedding.infrastructure.jpa.repositories.WeddingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryAdapter implements IUserRepository {

    private final UserRepository repository;
    private final WeddingRepository weddingRepository;
    private final UserEntityMapper mapper;

    public UserRepositoryAdapter(UserRepository repository,
                                 WeddingRepository weddingRepository,
                                 UserEntityMapper mapper) {
        this.repository = repository;
        this.weddingRepository = weddingRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);

        // ✅ Ensure foreign key integrity (user must belong to a wedding)
        if (user.getWeddingId() == null) {
            throw new IllegalArgumentException("User must be linked to a wedding (weddingId cannot be null)");
        }

        WeddingEntity wedding = weddingRepository.findById(user.getWeddingId())
                .orElseThrow(() -> new RuntimeException("Wedding not found with ID: " + user.getWeddingId()));

        entity.setWedding(wedding);

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public List<User> findByWeddingId(Long weddingId) {
        return repository.findByWeddingId(weddingId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
