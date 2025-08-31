package com.easywedding.webapi.controllers;

import com.easywedding.core.abstractServices.IUserService;
import com.easywedding.core.entities.User;
import com.easywedding.webapi.dtos.LoginRequestDto;
import com.easywedding.webapi.dtos.UserDto;
import com.easywedding.webapi.mappers.UserDtoMapper;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User created = userService.create(mapper.toDomain(userDto));
        return ResponseEntity.ok(mapper.toDto(created));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(mapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(userService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll().stream()
                .map(mapper::toDto).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(mapper.toDto(userService.getByUsername(username)));
    }

    @GetMapping("/wedding/{weddingId}")
    public ResponseEntity<List<UserDto>> getByWedding(@PathVariable Long weddingId) {
        return ResponseEntity.ok(userService.getByWeddingId(weddingId).stream()
                .map(mapper::toDto).collect(Collectors.toList()));
    }
}
