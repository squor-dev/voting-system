package com.example.votingsystem.repository;

import com.example.votingsystem.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    /**
     * Check if a given user has already voted in a voting.
     */
    boolean existsByVotingIdAndVoterId(Long votingId, Long voterId);

    /**
     * Count how many votes a candidate has in a voting.
     */
    long countByVotingIdAndCandidateId(Long votingId, Long candidateId);
}