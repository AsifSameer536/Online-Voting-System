package com.example.voter_service.controller;

import com.example.voter_service.entity.Vote;
import com.example.voter_service.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/votes")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    // Cast a vote: POST { "voterId": 1, "candidateId": 2 }
    @PostMapping("/cast")
    public ResponseEntity<?> cast(@RequestBody VoteRequest req) {
        try {
            Vote saved = voteService.castVote(req.getVoterId(), req.getCandidateId());
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Results: returns list of { candidateId, candidateName, votes }
    @GetMapping("/results")
    public List<ResultDto> results() {
        return voteService.getResults()
                .stream()
                .map(row -> new ResultDto(((Number)row[0]).longValue(), (String)row[1], ((Number)row[2]).longValue()))
                .collect(Collectors.toList());
    }

    // small DTOs
    public static class VoteRequest {
        private Long voterId;
        private Long candidateId;
        // getters/setters
        public Long getVoterId() { return voterId; }
        public void setVoterId(Long voterId) { this.voterId = voterId; }
        public Long getCandidateId() { return candidateId; }
        public void setCandidateId(Long candidateId) { this.candidateId = candidateId; }
    }

    public static class ResultDto {
        private Long candidateId;
        private String candidateName;
        private Long votes;
        public ResultDto(Long candidateId, String candidateName, Long votes) {
            this.candidateId = candidateId;
            this.candidateName = candidateName;
            this.votes = votes;
        }
        // getters
        public Long getCandidateId(){return candidateId;}
        public String getCandidateName(){return candidateName;}
        public Long getVotes(){return votes;}
    }
}
