package com.easywedding.infrastructure.jpa.entities;

import com.easywedding.core.enums.PermissionLevel;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // Foreign key to WeddingEntity
    @ManyToOne
    @JoinColumn(name = "wedding_id", referencedColumnName = "id", nullable = true)
    private WeddingEntity wedding;

    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;

    public UserEntity() {}

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public WeddingEntity getWedding() { return wedding; }
    public void setWedding(WeddingEntity wedding) { this.wedding = wedding; }

    public PermissionLevel getPermissionLevel() { return permissionLevel; }
    public void setPermissionLevel(PermissionLevel permissionLevel) { this.permissionLevel = permissionLevel; }
}
