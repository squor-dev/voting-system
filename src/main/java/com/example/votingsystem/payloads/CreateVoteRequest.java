package com.example.votingsystem.payloads;

import jakarta.validation.constraints.NotNull;

public class CreateVoteRequest {
    @NotNull public Long voterId;
    @NotNull public Long candidateId;
}
