package com.easywedding.infrastructure.jpa.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wedding")
public class WeddingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDate date;
    private String location;

    // One wedding has many users
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> users = new ArrayList<>();

    // One wedding has many guests
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuestEntity> guests = new ArrayList<>();

    // One wedding has many seating tables
    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatingTableEntity> seatingTables = new ArrayList<>();

    public WeddingEntity() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<UserEntity> getUsers() { return users; }
    public void setUsers(List<UserEntity> users) { this.users = users; }

    public List<GuestEntity> getGuests() { return guests; }
    public void setGuests(List<GuestEntity> guests) { this.guests = guests; }

    public List<SeatingTableEntity> getSeatingTables() { return seatingTables; }
    public void setSeatingTables(List<SeatingTableEntity> seatingTables) { this.seatingTables = seatingTables; }
}
