package com.easywedding.infrastructure.jpa.entities;

import com.easywedding.core.enums.RsvpStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "guest")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String notes;

    @Enumerated(EnumType.STRING)
    private RsvpStatus status;

    // Foreign key to WeddingEntity
    @ManyToOne
    @JoinColumn(name = "wedding_id", referencedColumnName = "id", nullable = true)
    private WeddingEntity wedding;

    // Foreign key to SeatingTableEntity
    @ManyToOne
    @JoinColumn(name = "table_number", referencedColumnName = "number", nullable = true)
    private SeatingTableEntity table;

    public GuestEntity() {}

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public RsvpStatus getStatus() { return status; }
    public void setStatus(RsvpStatus status) { this.status = status; }

    public WeddingEntity getWedding() { return wedding; }
    public void setWedding(WeddingEntity wedding) { this.wedding = wedding; }

    public SeatingTableEntity getTable() { return table; }
    public void setTable(SeatingTableEntity table) { this.table = table; }
}
