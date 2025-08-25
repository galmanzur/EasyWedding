package com.easywedding.core.abstractServices;

import com.easywedding.core.entities.Guest;
import java.util.List;

/**
 * Interface for guest-related operations.
 */
public interface IGuestService {
    Guest createGuest(Guest guest);
    /*List<Guest> getGuestsByWedding(Long weddingId);*/
    Guest updateGuest(Guest guest);
    void deleteGuest(Long guestId);
}
