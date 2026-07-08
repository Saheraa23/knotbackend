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
public class BoardActivity {
   
   @Id
   //@Min(value=1,message="id must be positive number starting from 1")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank(message="Action Description must have to enter")
   @Column(name="action_description")
   private String actionDescription;
 
   @Column(name="timestamp")
   private LocalDateTime timestamp;
   public BoardActivity(Long id, String actionDescription, LocalDateTime timestamp) {
    this.id = id;
    this.actionDescription = actionDescription;
    this.timestamp = timestamp;
   }
   public BoardActivity() {
   }
   public Long getId() {
    return id;
   }
   public void setId(Long id) {
    this.id = id;
   }
   public String getActionDescription() {
    return actionDescription;
   }
   public void setActionDescription(String actionDescription) {
    this.actionDescription = actionDescription;
   }
   public LocalDateTime getTimestamp() {
    return timestamp;
   }
   public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
   } 
}
