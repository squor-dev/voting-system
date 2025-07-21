package com.example.votingsystem.entity;

import jakarta.persistence.*;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int voteCount = 0;

    @ManyToOne
    @JoinColumn(name = "voting_id")
    private Voting voting;

    // Getters and Setters


    public void setName(String name) {
        this.name = name;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Voting getVoting() {
        return voting;
    }
}
