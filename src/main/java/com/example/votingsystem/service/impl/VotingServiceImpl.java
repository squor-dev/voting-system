package com.example.votingsystem.service.impl;

import com.example.votingsystem.entity.AppUser;
import com.example.votingsystem.entity.Candidate;
import com.example.votingsystem.entity.Voting;
import com.example.votingsystem.payloads.CreateVotingRequest;
import com.example.votingsystem.repository.AppUserRepository;
import com.example.votingsystem.repository.VotingRepository;
import com.example.votingsystem.service.VotingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotingServiceImpl implements VotingService {
    private final VotingRepository votingRepository;
    private final AppUserRepository  appUserRepository;

    public VotingServiceImpl(VotingRepository votingRepository,
                             AppUserRepository appUserRepository) {
        this.votingRepository  = votingRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<Voting> getAllVotings() {
        return votingRepository.findAll();
    }

    @Override
    public Voting createVoting(CreateVotingRequest req) {
        AppUser owner = appUserRepository.findById(req.ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner not found: " + req.ownerId));

        Voting voting = new Voting();
        voting.setTitle(req.title);
        voting.setOwner(owner);

        List<Candidate> candidateEntities = req.candidates.stream()
                .map(cd -> {
                    Candidate c = new Candidate();
                    c.setName(cd.name);
                    c.setVoting(voting);
                    return c;
                })
                .collect(Collectors.toList());

        voting.setCandidates(candidateEntities);
        return votingRepository.save(voting);
    }

    @Override
    public Voting closeVoting(Long votingId, Long userId) {
        Voting voting = votingRepository.findById(votingId)
                .orElseThrow(() -> new EntityNotFoundException("Voting not found: " + votingId));

        if (!voting.getOwner().getId().equals(userId)) {
            throw new SecurityException("You are not the owner of this voting.");
        }

        voting.setClosed(true);
        return votingRepository.save(voting);
    }
}
