package com.example.votingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Voting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    /** whether this voting is closed for further votes */
    private boolean closed = false;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private AppUser owner;

    @OneToMany(mappedBy = "voting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidate> candidates = new ArrayList<>();

    @OneToMany(mappedBy = "voting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    public Voting() {}

    // ——— ID ———
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // ——— Title ———
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    // ——— Closed flag ———
    public boolean isClosed() {
        return closed;
    }
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    // ——— Owner ———
    public AppUser getOwner() {
        return owner;
    }
    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    // ——— Candidates ———
    public List<Candidate> getCandidates() {
        return candidates;
    }
    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    // ——— Votes cast ———
    public List<Vote> getVotes() {
        return votes;
    }
    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}