package com.example.dog_api.entity;

public class Dog {
    private Long id;
    private String name;
    private String breed;
    private Integer age;
    private String color;

    public Dog(Long id, String name, String breed, Integer age, String color) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
