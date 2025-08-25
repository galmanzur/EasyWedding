package com.easywedding.core.entities;

import com.easywedding.core.enums.PermissionLevel;
import jakarta.persistence.*;

/**
 * Represents a system user with login credentials and permission level.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long userID;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;

    @ManyToOne
    @JoinColumn(name = "weddingID")
    private Wedding wedding;

    // Getters and setters...
}
