package com.example.voter_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "voters", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"voterId"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(nullable = false)
    private String voterId; // unique registration id

    private String password;

    private boolean hasVoted = false;

    public Voter() {}

    // Getters / Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getVoterId() { return voterId; }
    public void setVoterId(String voterId) { this.voterId = voterId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // boolean accessor names: provider code used isHasVoted() so include it
    public boolean isHasVoted() { return hasVoted; }
    public void setHasVoted(boolean hasVoted) { this.hasVoted = hasVoted; }
}
