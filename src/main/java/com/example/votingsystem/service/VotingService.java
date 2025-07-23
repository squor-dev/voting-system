package com.example.votingsystem.service;

import com.example.votingsystem.entity.Voting;
import com.example.votingsystem.payloads.CreateVotingRequest;

import java.util.List;


public interface VotingService {
    List<Voting> getAllVotings();
    Voting createVoting(CreateVotingRequest request);
    Voting closeVoting(Long votingId);
}
