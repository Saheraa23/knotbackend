package com.example.knot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
@Entity
public class BrainstormingBoard {

    @Id
    //@Min(value=1,message="id must be positive number starting from 1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Title must have to enter")
    @Column(nullable=false)
    private String title;
    
    @NotBlank(message="Description must have to enter")
    private String description;
  
    @Column(name="max_note_capacity")
    private Integer maxNoteCapacity;
    
    @Column(name="current_note_count")
    private Integer currentNoteCount;
  
    @Column(name="created_at")
    private LocalDateTime createdAt;
    public BrainstormingBoard(Long id, String title, String description, Integer maxNoteCapacity,
            Integer currentNoteCount, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.maxNoteCapacity = maxNoteCapacity;
        this.currentNoteCount = currentNoteCount;
        this.createdAt = createdAt;
    }
    public BrainstormingBoard() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getMaxNoteCapacity() {
        return maxNoteCapacity;
    }
    public void setMaxNoteCapacity(Integer maxNoteCapacity) {
        this.maxNoteCapacity = maxNoteCapacity;
    }
    public Integer getCurrentNoteCount() {
        return currentNoteCount;
    }
    public void setCurrentNoteCount(Integer currentNoteCount) {
        this.currentNoteCount = currentNoteCount;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
