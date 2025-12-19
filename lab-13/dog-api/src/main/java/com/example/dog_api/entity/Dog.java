package com.example.dog_api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Size;

@Document(collection = "dogs")
public class Dog {
    @Id
    private String id;
    private String name;
    @Size(min = 0, max = 50)
    private String breed;
    private Integer age;
    private String color;

    public Dog(String id, String name, String breed, Integer age, String color) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
