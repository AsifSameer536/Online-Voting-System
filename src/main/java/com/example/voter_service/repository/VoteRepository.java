package com.example.voter_service.repository;

import com.example.voter_service.entity.Vote;
import com.example.voter_service.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByVoter(Voter voter);

    // returns pairs: [candidateId, candidateName, voteCount]
    @Query("SELECT v.candidate.id, v.candidate.name, COUNT(v) FROM Vote v GROUP BY v.candidate.id, v.candidate.name")
    List<Object[]> countVotesPerCandidate();
}
