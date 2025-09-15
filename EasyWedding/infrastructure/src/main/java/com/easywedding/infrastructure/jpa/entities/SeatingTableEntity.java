package com.easywedding.infrastructure.jpa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "seating_table")
public class SeatingTableEntity {

    @Id
    private Long number;

    private String name;

    @Column(name = "wedding_id")
    @JoinColumn(name = "wedding_id", referencedColumnName = "id", nullable = true)
    private Long weddingId;

    public SeatingTableEntity() {}

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

    public Long getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Long weddingId) {
        this.weddingId = weddingId;
    }
}
