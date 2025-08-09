package com.example.voter_service.service;

import com.example.voter_service.entity.Candidate;
import com.example.voter_service.repository.CandidateRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate add(Candidate c) {
        return candidateRepository.save(c);
    }

    public List<Candidate> getAll() {
        return candidateRepository.findAll();
    }
}
