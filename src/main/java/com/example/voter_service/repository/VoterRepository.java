package com.example.voter_service.repository;

import com.example.voter_service.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByVoterId(String voterId);
    Optional<Voter> findByEmail(String email);
    boolean existsByVoterId(String voterId);
    boolean existsByEmail(String email);
}
