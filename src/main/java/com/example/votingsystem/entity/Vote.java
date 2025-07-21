package com.example.votingsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "user_id", "voting_id" }
        )
)
public class Vote {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne @JoinColumn(name="user_id")
    private AppUser user;

    @ManyToOne @JoinColumn(name="voting_id")
    private Voting voting;

    @ManyToOne @JoinColumn(name="candidate_id")
    private Candidate candidate;

    private LocalDateTime castAt = LocalDateTime.now();

    // getters / setters
}