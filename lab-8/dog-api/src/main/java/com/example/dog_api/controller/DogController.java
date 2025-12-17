package com.example.dog_api.controller;

import com.example.dog_api.entity.Dog;
import com.example.dog_api.repository.DogRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final DogRepository dogRepository;

    public DogController(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }


    @Tag(name = "Read Operations")
    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable String id) {
        return dogRepository.findById(id).orElse(null);
    }

    @Tag(name = "Read Operations")
    @GetMapping
    public Collection<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    @Tag(name = "Write Operations")
    @PostMapping
    public void addDog(@RequestBody Dog dog) {
        dogRepository.save(dog);
    }

    @Tag(name = "Write Operations")
    @PutMapping("/{id}")
    public void updateDog(@PathVariable String id, @RequestBody Dog dog) {
        var existingDog = dogRepository.findById(id).orElse(null);
        if (existingDog == null) {
            return;
        }
        dog.setId(id);
        dogRepository.save(dog);
    }

    @Tag(name = "Write Operations")
    @DeleteMapping("/{id}")
    public void deleteDog(@PathVariable String id) {
        dogRepository.deleteById(id);
    }
}