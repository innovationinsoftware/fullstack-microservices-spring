package com.example.human_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    private boolean owner;
    private boolean adoptingOut;
    private boolean adoptingIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean isAdoptingOut() {
        return adoptingOut;
    }

    public void setAdoptingOut(boolean adoptingOut) {
        this.adoptingOut = adoptingOut;
    }

    public boolean isAdoptingIn() {
        return adoptingIn;
    }

    public void setAdoptingIn(boolean adoptingIn) {
        this.adoptingIn = adoptingIn;
    }
}
