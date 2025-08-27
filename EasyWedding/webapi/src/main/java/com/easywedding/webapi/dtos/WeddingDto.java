package com.easywedding.webapi.dtos;

import java.time.LocalDate;

public class WeddingDto {
    private Long id;
    private String description;
    private LocalDate date;
    private String location;

    // Constructors
    public WeddingDto() {}

    public WeddingDto(Long id, String description, LocalDate date, String location) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
