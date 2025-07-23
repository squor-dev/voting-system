package com.example.votingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Which voting this vote belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "voting_id", nullable = false)
    @JsonIgnore
    private Voting voting;

    /**
     * Which candidate was chosen.
     */
    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    @JsonIgnore
    private Candidate candidate;

    /**
     * Who cast the vote.
     */
    @ManyToOne
    @JoinColumn(name = "voter_id", nullable = false)
    @JsonIgnore
    private AppUser voter;

    public Vote() { }

    // ——— ID ———
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // ——— Voting ———
    public Voting getVoting() {
        return voting;
    }
    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    // ——— Candidate ———
    public Candidate getCandidate() {
        return candidate;
    }
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    // ——— Voter ———
    public AppUser getVoter() {
        return voter;
    }
    public void setVoter(AppUser voter) {
        this.voter = voter;
    }
}
