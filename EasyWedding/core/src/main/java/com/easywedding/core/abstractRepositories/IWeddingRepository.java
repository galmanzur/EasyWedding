package com.easywedding.core.abstractRepositories;

import com.easywedding.core.entities.Wedding;

public interface IWeddingRepository {
    Wedding save(Wedding wedding);
    Wedding findById(Long id);
    void delete(Long id);
}
