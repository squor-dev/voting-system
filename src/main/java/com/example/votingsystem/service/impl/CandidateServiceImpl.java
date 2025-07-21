package com.example.votingsystem.service.impl;

import com.example.votingsystem.entity.Candidate;
import com.example.votingsystem.repository.CandidateRepository;
import com.example.votingsystem.service.CandidateService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
}
