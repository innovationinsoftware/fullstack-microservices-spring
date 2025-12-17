package com.example.dog_api.repository;

import com.example.dog_api.entity.Dog;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DogRepository extends MongoRepository<Dog, String> {
}
