package com.easywedding.webapi.controllers;

import com.easywedding.core.abstractServices.IUserService;
import com.easywedding.core.entities.User;
import com.easywedding.webapi.dtos.LoginRequestDto;
import com.easywedding.webapi.dtos.UserDto;
import com.easywedding.webapi.mappers.UserDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;
    private final UserDtoMapper mapper;

    public UserController(IUserService userService, UserDtoMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Operation(summary = "Create a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @Valid @RequestBody UserDto userDto
    ) {
        User created = userService.create(mapper.toDomain(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(created));
    }

    @Operation(summary = "Authenticate user with username and password")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "401", description = "Invalid username or password")
    })
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(
            @Valid @RequestBody LoginRequestDto loginRequest
    ) {
        User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(mapper.toDto(user));
    }

    @Operation(summary = "Get user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(
            @Parameter(name = "id", description = "User ID", required = true)
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(mapper.toDto(userService.getById(id)));
    }

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "List of users")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(
                userService.getAll().stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @Operation(summary = "Delete user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(name = "id", description = "User ID to delete", required = true)
            @PathVariable("id") Long id
    ) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get user by username")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getByUsername(
            @Parameter(name = "username", description = "Username to search", required = true)
            @PathVariable("username") String username
    ) {
        return ResponseEntity.ok(mapper.toDto(userService.getByUsername(username)));
    }

    @Operation(summary = "Get users by wedding ID")
    @ApiResponse(responseCode = "200", description = "Users found")
    @GetMapping("/wedding/{weddingId}")
    public ResponseEntity<List<UserDto>> getByWeddingId(
            @Parameter(name = "weddingId", description = "Wedding ID", required = true)
            @PathVariable("weddingId") Long weddingId
    ) {
        return ResponseEntity.ok(
                userService.getByWeddingId(weddingId).stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList())
        );
    }
}
