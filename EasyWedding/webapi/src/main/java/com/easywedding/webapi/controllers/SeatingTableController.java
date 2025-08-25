package com.easywedding.webapi.controllers;

import com.easywedding.core.abstractServices.ISeatingTableService;
import com.easywedding.core.entities.SeatingTable;
import com.easywedding.webapi.dtos.SeatingTableDto;
import com.easywedding.webapi.mappers.SeatingTableDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tables")
public class SeatingTableController {

    private final ISeatingTableService service;
    private final SeatingTableDtoMapper mapper;

    public SeatingTableController(ISeatingTableService service, SeatingTableDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(
            summary = "Create a new seating table",
            description = "Receives a SeatingTableDto with a table number and wedding ID, saves it, and returns the created table."
    )
    public SeatingTableDto create(@RequestBody SeatingTableDto dto) {
        SeatingTable created = service.create(mapper.toDomain(dto));
        return mapper.toDto(created);
    }

    @GetMapping("/wedding/{weddingId}")
    @Operation(
            summary = "Get all seating tables for a wedding",
            description = "Returns all seating tables associated with the specified wedding ID."
    )
    public List<SeatingTableDto> getByWedding(@PathVariable("weddingId") Long weddingId) {
        return service.getByWeddingId(weddingId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{number}")
    @Operation(
            summary = "Delete a seating table",
            description = "Deletes a seating table using its table number."
    )
    public void delete(
            @Parameter(description = "Number Of Table To Delete")
            @PathVariable("number") Long number
    ) {
        service.delete(number);
    }
}
