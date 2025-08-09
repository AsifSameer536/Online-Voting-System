package com.example.voter_service.controller;

import com.example.voter_service.entity.Candidate;
import com.example.voter_service.service.CandidateService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/register")
    public Candidate register(@RequestBody Candidate candidate) {
        return candidateService.add(candidate);
    }

    @GetMapping
    public List<Candidate> getAll() {
        return candidateService.getAll();
    }
}
