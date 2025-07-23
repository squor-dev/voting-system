package com.example.votingsystem.service;

import com.example.votingsystem.entity.Vote;
import jakarta.persistence.EntityNotFoundException;

public interface VoteService {
    /**
     * Casts a vote for candidateId in votingId by voterId.
     * @throws IllegalStateException if the voting is closed or the user has already voted
     * @throws EntityNotFoundException if any of voting, candidate or user doesn’t exist
     */
    Vote castVote(Long votingId, Long voterId, Long candidateId);
}