package com.easywedding.core.abstractServices;

import com.easywedding.core.entities.Wedding;

import java.util.List;

public interface IWeddingService {
    Wedding create(Wedding wedding);
    Wedding getById(Long id);
    List<Wedding> findAll();
    void delete(Long id);
}
