package com.easywedding.core.abstractRepositories;

import com.easywedding.core.entities.Guest;
import java.util.List;

public interface IGuestRepository {
    Guest save(Guest guest);
    /*List<Guest> findByWeddingId(Long weddingId); */
    void delete(Long guestId);
}
