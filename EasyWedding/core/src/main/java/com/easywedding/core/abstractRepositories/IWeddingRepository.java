package com.easywedding.core.abstractRepositories;

import com.easywedding.core.entities.Wedding;

import java.util.List;

public interface IWeddingRepository {
    Wedding save(Wedding wedding);
    Wedding findById(Long id);
    List<Wedding> findAll();
    void delete(Long id);
}
