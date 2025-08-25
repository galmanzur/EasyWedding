package com.easywedding.infrastructure.jpa.entities;

import jakarta.persistence.*;
import com.easywedding.core.enums.RsvpStatus;

@Entity
@Table(name = "Guest")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String notes;

    @Enumerated(EnumType.STRING)
    private RsvpStatus status;

    @Column(name = "wedding_id")
    private Long weddingId;

    @Column(name = "table_id")
    private Long tableId;

    // --- Getters and Setters ---

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

    public Long getWeddingId() { return weddingId; }
    public void setWeddingId(Long weddingId) { this.weddingId = weddingId; }

    public Long getTableId() { return tableId; }
    public void setTableId(Long tableId) { this.tableId = tableId; }
}
