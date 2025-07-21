package com.example.votingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private boolean closed = false;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private AppUser owner;

    @OneToMany(mappedBy = "voting", cascade = CascadeType.ALL)
    private List<Candidate> candidates;

    @OneToMany(mappedBy="voting", cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();
    // Getters and Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public AppUser getOwner() {
        return owner;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }
}
