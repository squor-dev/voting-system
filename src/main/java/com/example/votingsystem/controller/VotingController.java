package com.example.votingsystem.controller;

import com.example.votingsystem.entity.Voting;
import com.example.votingsystem.payloads.CreateVotingRequest;
import com.example.votingsystem.service.VotingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votings")
public class VotingController {
    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @GetMapping
    public List<Voting> getVotings() {
        return votingService.getAllVotings();
    }

    @PostMapping
    public Voting createVoting(@RequestBody @Valid CreateVotingRequest req) {
        return votingService.createVoting(req);
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<Voting> closeVoting(@PathVariable Long id, @RequestHeader("X-User-Id") Long userId) {
        Voting closed = votingService.closeVoting(id, userId);
        return ResponseEntity.ok(closed);
    }
}
