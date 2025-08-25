package com.easywedding.infrastructure.services;

import com.easywedding.core.entities.Guest;
import com.easywedding.core.abstractRepositories.IGuestRepository;
import com.easywedding.core.abstractServices.IGuestService;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements IGuestService {

    private final IGuestRepository guestRepository;

    public GuestServiceImpl(IGuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    /*@Override
    public List<Guest> getGuestsByWedding(Long weddingId) {
        return guestRepository.findByWeddingId(weddingId);
    }*/

    @Override
    public Guest updateGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public void deleteGuest(Long guestId) {
        guestRepository.delete(guestId);
    }
}
