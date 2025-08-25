package com.easywedding.infrastructure.jpa.entities;

import com.easywedding.core.enums.RsvpStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "Guest")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guestId")
    private Long id;

    @Column(name = "guestName")
    private String name;

    private String phoneNumber;

    private String notes;

    @Enumerated(EnumType.STRING)
    private RsvpStatus status;

    private Integer tableNumber;

    @Column(name = "weddingID")
    private Long weddingId;

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public RsvpStatus getRsvpStatus() {
        return status;
    }

    public void setRsvpStatus(RsvpStatus status) {
        this.status = status;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Long getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Long weddingId) {
        this.weddingId = weddingId;
    }
}
