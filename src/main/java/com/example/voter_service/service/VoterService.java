package com.example.voter_service.service;

import com.example.voter_service.entity.Voter;
import com.example.voter_service.repository.VoterRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VoterService {
    private final VoterRepository voterRepository;

    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    public Voter register(Voter voter) {
        if (voterRepository.existsByVoterId(voter.getVoterId())) {
            throw new IllegalArgumentException("VoterId already exists");
        }
        if (voterRepository.existsByEmail(voter.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        // TODO: hash the password with BCrypt if you add Spring Security later
        return voterRepository.save(voter);
    }

    public List<Voter> getAll() {
        return voterRepository.findAll();
    }

    public Optional<Voter> findById(Long id) {
        return voterRepository.findById(id);
    }

    public Optional<Voter> findByVoterId(String voterId) {
        return voterRepository.findByVoterId(voterId);
    }

    public void markVoted(Voter voter) {
        voter.setHasVoted(true);
        voterRepository.save(voter);
    }
}
