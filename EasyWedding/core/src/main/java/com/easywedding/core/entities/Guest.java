package com.easywedding.core.entities;

import com.easywedding.core.enums.RsvpStatus;

public class Guest {
    private Long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private RsvpStatus status;
    private Long weddingId;
    private Long tableNumber;

    public Guest() {}

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

    public Long getTableNumber() { return tableNumber; }
    public void setTableNumber(Long tableNumber) { this.tableNumber = tableNumber; }
}
