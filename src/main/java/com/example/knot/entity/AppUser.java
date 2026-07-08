package com.example.knot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class AppUser {

    @Id
   //@Min(value = 1, message = "Id must be positive")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "Username must be 4-20 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "domain_role", nullable = false)
    private DomainRole domainRole;

    public AppUser() {
    }

    public AppUser(Long id, String username, String email, String password, DomainRole domainRole) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.domainRole = domainRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DomainRole getDomainRole() {
        return domainRole;
    }

    public void setDomainRole(DomainRole domainRole) {
        this.domainRole = domainRole;
    }
}
