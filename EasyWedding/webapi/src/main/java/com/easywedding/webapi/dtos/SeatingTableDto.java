package com.easywedding.webapi.dtos;

public class SeatingTableDto {
    private Long number;
    private String name;
    private Long weddingId;

    public SeatingTableDto() {
    }

    public SeatingTableDto(Long id, String name, Long weddingId) {
        this.number = id;
        this.name = name;
        this.weddingId = weddingId;
    }

    public Long getNumber() { return number; }
    public void setNumber(Long number) { this.number = number; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getWeddingId() { return weddingId; }
    public void setWeddingId(Long weddingId) { this.weddingId = weddingId; }
}
