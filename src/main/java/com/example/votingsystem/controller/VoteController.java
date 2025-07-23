// src/main/java/com/example/votingsystem/controller/VoteController.java
package com.example.votingsystem.controller;

import com.example.votingsystem.payloads.CreateVoteRequest;
import com.example.votingsystem.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/votings/{votingId}/votes")
@Validated
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Void> castVote(
            @PathVariable Long votingId,
            @Valid @RequestBody CreateVoteRequest req
    ) {
        voteService.castVote(votingId, req.voterId, req.candidateId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
