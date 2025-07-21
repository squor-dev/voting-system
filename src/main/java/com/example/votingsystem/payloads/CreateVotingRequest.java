package com.example.votingsystem.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CreateVotingRequest {
    @NotBlank
    public String title;

    public Long ownerId;

    @Valid
    public List<CandidateData> candidates;

    public static class CandidateData {
        @NotBlank
        public String name;
    }
}
