package com.easywedding.webapi.dtos;

import com.easywedding.core.enums.RsvpStatus;

public class GuestDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private RsvpStatus status;
    private String notes;
    private Long weddingId;
    private Long tableId;

    // Constructors
    public GuestDto() {}

    public GuestDto(Long id, String name, String phoneNumber, RsvpStatus status, String notes, Long weddingId, Long tableId) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.notes = notes;
        this.weddingId = weddingId;
        this.tableId = tableId;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public RsvpStatus getStatus() { return status; }
    public void setStatus(RsvpStatus status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Long getWeddingId() { return weddingId; }
    public void setWeddingId(Long weddingId) { this.weddingId = weddingId; }

    public Long getTableId() { return tableId; }
    public void setTableId(Long tableId) { this.tableId = tableId; }
}
