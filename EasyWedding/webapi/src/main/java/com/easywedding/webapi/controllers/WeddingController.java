package com.easywedding.webapi.controllers;

import com.easywedding.core.abstractServices.IWeddingService;
import com.easywedding.core.entities.Wedding;
import com.easywedding.webapi.dtos.WeddingDto;
import com.easywedding.webapi.mappers.WeddingDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/weddings")
public class WeddingController {

    private final IWeddingService service;
    private final WeddingDtoMapper mapper;

    public WeddingController(IWeddingService service, WeddingDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @Operation(
            summary = "Create a new wedding",
            description = "Receives a WeddingDto with description, date and location, saves it, and returns the created wedding."
    )
    public WeddingDto create(@RequestBody WeddingDto dto) {
        Wedding created = service.create(mapper.toDomain(dto));
        return mapper.toDto(created);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a wedding by ID",
            description = "Fetches a wedding from the system by its ID and returns it as WeddingDto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wedding found"),
            @ApiResponse(responseCode = "404", description = "Wedding not found")
    })
    public WeddingDto getById(
            @Parameter(description = "The ID of the wedding to retrieve", required = true)
            @PathVariable("id") Long id
    ) {
        return mapper.toDto(service.getById(id));
    }

    @GetMapping
    @Operation(
            summary = "Get all weddings",
            description = "Retrieves all weddings in the system and returns them as a list of WeddingDto."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of weddings returned successfully")
    })
    public List<WeddingDto> getAll() {
        return service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a wedding",
            description = "Deletes a wedding from the system based on the provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wedding deleted"),
            @ApiResponse(responseCode = "404", description = "Wedding not found")
    })
    public void delete(
            @Parameter(description = "The ID of the wedding to delete", required = true)
            @PathVariable("id") Long id
    ) {
        service.delete(id);
    }
}
