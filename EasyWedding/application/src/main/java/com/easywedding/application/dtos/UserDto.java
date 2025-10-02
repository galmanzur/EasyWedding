package com.easywedding.application.dtos;

import com.easywedding.core.enums.PermissionLevel;

/**
 * UserDto used in the JavaFX client application.
 * Mirrors the WebAPI UserDto but is used for sending/receiving
 * user data between the UI and the REST API.
 */
public class UserDto {

    private Long id; // The user's ID in the system
    private String username; // The user's username
    private String password; // The user's password (plain for signup)
    private Long weddingId; // The wedding ID this user belongs to
    private PermissionLevel permissionLevel; // The user's permission level (enum)

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Long weddingId) {
        this.weddingId = weddingId;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(PermissionLevel permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", weddingId=" + weddingId +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
