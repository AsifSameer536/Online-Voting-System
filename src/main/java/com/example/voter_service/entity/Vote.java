package com.example.voter_service.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"voter_id"})
})
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "voter_id")
    private Voter voter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private Instant castAt = Instant.now();

    public Vote() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Voter getVoter() { return voter; }
    public void setVoter(Voter voter) { this.voter = voter; }

    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }

    public Instant getCastAt() { return castAt; }
    public void setCastAt(Instant castAt) { this.castAt = castAt; }
}
