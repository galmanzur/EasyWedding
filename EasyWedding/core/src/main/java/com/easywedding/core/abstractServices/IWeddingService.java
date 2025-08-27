package com.easywedding.core.abstractServices;

import com.easywedding.core.entities.Wedding;

public interface IWeddingService {
    Wedding create(Wedding wedding);
    Wedding getById(Long id);
    void delete(Long id);
}
