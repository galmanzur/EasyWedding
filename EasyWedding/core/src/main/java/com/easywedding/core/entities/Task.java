package com.easywedding.core.entities;

import com.easywedding.core.enums.TaskStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Represents a planning task for the wedding.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long taskID;

    private String description;
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "weddingID")
    private Wedding wedding;

    // Getters and setters...
}
