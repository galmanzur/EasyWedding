package com.easywedding.application.dtos;

public class GuestDto {
    private Long id;
    private String name;
    private String phone;
    private Boolean attending;
    private Integer tableNumber;
    private String notes;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Boolean getAttending() { return attending; }
    public void setAttending(Boolean attending) { this.attending = attending; }

    public Integer getTableNumber() { return tableNumber; }
    public void setTableNumber(Integer tableNumber) { this.tableNumber = tableNumber; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
