package com.easywedding.core.entities;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Represents a table at the wedding.
 */
@Entity
public class SeatingTable {

    @Id
    private Integer tableNumber;

    private int maxGuests;

    @ManyToOne
    @JoinColumn(name = "weddingID")
    private Wedding wedding;

    @OneToMany(mappedBy = "table")
    private Set<Guest> guests;

    // Getters and setters...
}
