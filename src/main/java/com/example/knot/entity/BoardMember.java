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

public class BoardMember {

    @Id
   // @Min(value=1,message="id must be positive number starting from 1")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Access level must have to enter")
    @Column(name="access_level")
    private String accessLevel;

    @Column(name="joined_at")
    private LocalDateTime joinedAt;
    public BoardMember(Long id, String accessLevel, LocalDateTime joinedAt) {
        this.id = id;
        this.accessLevel = accessLevel;
        this.joinedAt = joinedAt;
    }
    public BoardMember() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAccessLevel() {
        return accessLevel;
    }
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }
    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
