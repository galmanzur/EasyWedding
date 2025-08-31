package com.easywedding.webapi.controllers;

import com.easywedding.core.abstractServices.IUserService;
import com.easywedding.core.entities.User;
import com.easywedding.webapi.dtos.LoginRequestDto;
import com.easywedding.webapi.dtos.UserDto;
import com.easywedding.webapi.mappers.UserDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User created = userService.create(mapper.toDomain(userDto));
        return ResponseEntity.ok(mapper.toDto(created));
    }

    @Operation(summary = "Authenticate user with username and password")
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequest) {
        User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(mapper.toDto(user));
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(
            @Parameter(description = "User ID to retrieve")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(mapper.toDto(userService.getById(id)));
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getAll().stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
