package com.example.votingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy="owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voting> ownElections;

    public void setId(Long id) {
        this.id = id;
    }
    // Getters and Setters


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Voting> getOwnElections() {
        return ownElections;
    }

    public void setOwnElections(List<Voting> ownElections) {
        this.ownElections = ownElections;
    }
}
