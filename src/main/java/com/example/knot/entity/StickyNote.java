package com.example.knot.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
//import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
@Entity
public class StickyNote {

    @Id
   // @Min(value=1,message="id must be positive number starting from 1")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Content must have to enter")
    @Column(nullable=false)
    private String content;

    @NotBlank(message="Color code must have to enter")
    @Column(nullable=false)
    private String colorCode;

    @Column(name = "x_pos", nullable = false)
    @JsonProperty("xPos")
    private Integer xPos;

    @Column(name = "y_pos", nullable = false)
    @JsonProperty("yPos")
    private Integer yPos;

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;
 
    @Column(name="created_at")
    private LocalDateTime createdAt;

    public StickyNote(Long id, String content, String colorCode, Integer xPos, Integer yPos, Long version,
            LocalDateTime deletedAt, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.colorCode = colorCode;
        this.xPos = xPos;
        this.yPos = yPos;
        this.version = version;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
    }
    public StickyNote() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getColorCode() {
        return colorCode;
    }
    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
    @JsonProperty("xPos")
    public Integer getXPos() {
        return xPos;
    }
    public void setXPos(Integer xPos) {
        this.xPos = xPos;
    }
    @JsonProperty("yPos")
    public Integer getYPos() {
        return yPos;
    }
    public void setYPos(Integer yPos) {
        this.yPos = yPos;
    }
    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
