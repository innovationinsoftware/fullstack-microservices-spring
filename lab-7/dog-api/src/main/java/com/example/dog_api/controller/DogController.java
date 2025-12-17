package com.example.dog_api.controller;

import com.example.dog_api.entity.Dog;
import com.example.dog_api.repository.CanineContract;
import com.example.dog_api.repository.DogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final CanineContract<Dog> dogRepository;

    public DogController(CanineContract<Dog> dogRepository) {
        this.dogRepository = dogRepository;
    }

    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable Long id) {
        return dogRepository.findById(id);
    }

    @GetMapping
    public Collection<Dog> getAllDogs() {
        if (dogRepository instanceof DogRepository) {
            return ((DogRepository) dogRepository).getAll();
        }
        return null;
    }

    @PostMapping
    public void addDog(@RequestBody Dog dog) {
        dogRepository.save(dog);
    }

    @PutMapping("/{id}")
    public void updateDog(@PathVariable Long id, @RequestBody Dog dog) {
        dogRepository.update(id, dog);
    }

    @DeleteMapping("/{id}")
    public void deleteDog(@PathVariable Long id) {
        dogRepository.deleteById(id);
    }
}