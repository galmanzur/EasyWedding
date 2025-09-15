package com.easywedding.infrastructure.jpa.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seating_table")
public class SeatingTableEntity {

    @Id
    private Long number;

    private String name;

    @ManyToOne
    @JoinColumn(name = "wedding_id", referencedColumnName = "id", nullable = true)
    private WeddingEntity wedding;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuestEntity> guests = new ArrayList<>();

    public SeatingTableEntity() {}

    // Getters & Setters
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeddingEntity getWedding() {
        return wedding;
    }

    public void setWedding(WeddingEntity wedding) {
        this.wedding = wedding;
    }

    public List<GuestEntity> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestEntity> guests) {
        this.guests = guests;
    }
}
