package com.easywedding.webapi.controllers;

import com.easywedding.core.abstractServices.IGuestService;
import com.easywedding.core.entities.Guest;
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

    @PostMapping
    public ResponseEntity<GuestDto> createGuest(@RequestBody GuestDto guestDto) {
        Guest guest = guestDtoMapper.toDomain(guestDto);
        Guest saved = guestService.createGuest(guest);
        return ResponseEntity.ok(guestDtoMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestDto> updateGuest(
            @PathVariable(name = "id") Long id,
            @RequestBody GuestDto guestDto) {
        guestDto.setId(id);
        Guest updated = guestService.updateGuest(guestDtoMapper.toDomain(guestDto));
        return ResponseEntity.ok(guestDtoMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable(name = "id") Long id) {
        guestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getGuestById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(guestDtoMapper.toDto(guestService.getById(id)));
    }

    @GetMapping("/wedding/{weddingId}")
    public ResponseEntity<List<GuestDto>> getGuestsByWeddingId(
            @PathVariable(name = "weddingId") Long weddingId) {
        List<GuestDto> result = guestService.getByWeddingId(weddingId).stream()
                .map(guestDtoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<GuestDto>> searchGuestsByName(
            @RequestParam(name = "name") String name) {
        List<GuestDto> result = guestService.searchByName(name).stream()
                .map(guestDtoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/table/{tableNumber}")
    public ResponseEntity<List<GuestDto>> getGuestsByTableNumber(
            @PathVariable(name = "tableNumber") Long tableNumber) {
        List<GuestDto> result = guestService.getByTableNumber(tableNumber).stream()
                .map(guestDtoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/table/{tableNumber}/count")
    public ResponseEntity<Long> countGuestsByTableNumber(
            @PathVariable(name = "tableNumber") Long tableNumber) {
        return ResponseEntity.ok(guestService.countByTableNumber(tableNumber));
    }
}
