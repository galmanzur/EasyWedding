package com.easywedding.core.abstractServices;

import com.easywedding.core.entities.User;

import java.util.List;

public interface IUserService {
    User create(User user);
    User getById(Long id);
    List<User> getAll();
    void delete(Long id);
    User authenticate(String username, String password);
    User getByUsername(String username);
    List<User> getByWeddingId(Long weddingId);
}
