package com.easywedding.core.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a wedding event.
 */
@Entity
public class Wedding {

    @Id
    @GeneratedValue
    private Long weddingID;

    private String description;
    private LocalDate date;
    private String location;

    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL)
    private Set<User> users;

    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL)
    private Set<Guest> guests;

    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL)
    private Set<Task> tasks;

    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL)
    private Set<SeatingTable> tables;

    // Getters and setters...
}
