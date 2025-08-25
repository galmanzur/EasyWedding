package com.easywedding.webapi.controllers;

import com.easywedding.core.entities.Guest;
import com.easywedding.core.abstractServices.IGuestService;
import com.easywedding.webapi.dtos.GuestDto;
import com.easywedding.webapi.mappers.GuestDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final IGuestService guestService;
    private final GuestDtoMapper guestDtoMapper;

    public GuestController(IGuestService guestService, GuestDtoMapper guestDtoMapper) {
        this.guestService = guestService;
        this.guestDtoMapper = guestDtoMapper;
    }

    /*@GetMapping("/wedding/{weddingId}")
    public ResponseEntity<List<GuestDto>> getGuestsByWedding(@PathVariable Long weddingId) {
        List<GuestDto> result = guestService.getGuestsByWedding(weddingId).stream()
                .map(guestDtoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }*/

    @PostMapping
    public ResponseEntity<GuestDto> createGuest(@RequestBody GuestDto guestDto) {
        Guest guest = guestDtoMapper.toDomain(guestDto);
        Guest saved = guestService.createGuest(guest);
        return ResponseEntity.ok(guestDtoMapper.toDto(saved));
    }
}
