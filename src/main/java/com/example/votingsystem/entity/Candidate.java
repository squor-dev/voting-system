package com.example.votingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "voting_id", nullable = false)
    private Voting voting;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    public Candidate() {}

    // ——— ID ———
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // ——— Name ———
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // ——— Parent Voting ———
    public Voting getVoting() {
        return voting;
    }
    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    // ——— Votes received ———
    public List<Vote> getVotes() {
        return votes;
    }
    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}