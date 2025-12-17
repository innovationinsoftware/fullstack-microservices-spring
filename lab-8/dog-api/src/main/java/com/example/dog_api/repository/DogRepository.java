package com.example.dog_api.repository;

import org.springframework.stereotype.Repository;

import com.example.dog_api.entity.Dog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DogRepository implements CanineContract<Dog> {
    private Map<Long, Dog> dogStorage = new HashMap<>();

    public DogRepository() {
        Dog dog1 = new Dog(1L, "Ringo", "Golden Retriever", 3, "Golden");
        Dog dog2 = new Dog(2L, "Mimi", "German Shepherd", 5, "Black and Tan");
        dogStorage.put(dog1.getId(), dog1);
        dogStorage.put(dog2.getId(), dog2);
    }

    @Override
    public void save(Dog canine) {
        dogStorage.put(canine.getId(), canine);
    }

    @Override
    public Dog findById(Long id) {
        return dogStorage.get(id);
    }

    @Override
    public Collection<Dog> getAll() {
        return dogStorage.values();
    }

    @Override
    public void update(Long id, Dog canine) {
        canine.setId(id);
        dogStorage.put(id, canine);
    }

    @Override
    public void deleteById(Long id) {
        dogStorage.remove(id);
    }
}
