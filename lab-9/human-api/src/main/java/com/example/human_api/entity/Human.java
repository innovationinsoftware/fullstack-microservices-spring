package com.example.human_api.entity;

public class Human {
    private Long id;
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
