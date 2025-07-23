// src/main/java/com/example/votingsystem/service/impl/VoteServiceImpl.java
package com.example.votingsystem.service.impl;

import com.example.votingsystem.entity.AppUser;
import com.example.votingsystem.entity.Candidate;
import com.example.votingsystem.entity.Vote;
import com.example.votingsystem.entity.Voting;
import com.example.votingsystem.repository.AppUserRepository;
import com.example.votingsystem.repository.CandidateRepository;
import com.example.votingsystem.repository.VoteRepository;
import com.example.votingsystem.repository.VotingRepository;
import com.example.votingsystem.service.VoteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final VotingRepository votingRepository;
    private final CandidateRepository candidateRepository;
    private final AppUserRepository userRepository;

    public VoteServiceImpl(VoteRepository voteRepository,
                           VotingRepository votingRepository,
                           CandidateRepository candidateRepository,
                           AppUserRepository userRepository) {
        this.voteRepository       = voteRepository;
        this.votingRepository     = votingRepository;
        this.candidateRepository  = candidateRepository;
        this.userRepository       = userRepository;
    }

    @Override
    @Transactional
    public Vote castVote(Long votingId, Long voterId, Long candidateId) {
        // 1) no double voting
        if (voteRepository.existsByVotingIdAndVoterId(votingId, voterId)) {
            throw new IllegalStateException("User " + voterId + " has already voted in voting " + votingId);
        }

        // 2) load voting
        Voting voting = votingRepository.findById(votingId)
                .orElseThrow(() -> new EntityNotFoundException("Voting not found: " + votingId));
        if (voting.isClosed()) {
            throw new IllegalStateException("Voting " + votingId + " is closed");
        }

        // 3) load candidate, ensure it belongs to this voting
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found: " + candidateId));
        if (!candidate.getVoting().getId().equals(votingId)) {
            throw new IllegalArgumentException("Candidate " + candidateId + " does not belong to voting " + votingId);
        }

        // 4) load user
        AppUser voter = userRepository.findById(voterId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + voterId));

        // 5) save vote
        Vote vote = new Vote();
        vote.setVoting(voting);
        vote.setCandidate(candidate);
        vote.setVoter(voter);
        return voteRepository.save(vote);
    }
}
