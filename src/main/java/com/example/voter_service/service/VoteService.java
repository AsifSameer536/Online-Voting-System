package com.example.voter_service.service;

import com.example.voter_service.entity.Candidate;
import com.example.voter_service.entity.Vote;
import com.example.voter_service.entity.Voter;
import com.example.voter_service.repository.CandidateRepository;
import com.example.voter_service.repository.VoteRepository;
import com.example.voter_service.repository.VoterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    public VoteService(VoteRepository voteRepository, VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public Vote castVote(Long voterId, Long candidateId) {
        Voter voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new IllegalArgumentException("Voter not found"));

        if (voter.isHasVoted() || voteRepository.existsByVoter(voter)) {
            throw new IllegalStateException("Voter has already voted");
        }

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));

        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setCandidate(candidate);

        Vote saved = voteRepository.save(vote);

        // mark voter as having voted
        voter.setHasVoted(true);
        voterRepository.save(voter);

        return saved;
    }

    public List<Object[]> getResults() {
        return voteRepository.countVotesPerCandidate();
    }
}
