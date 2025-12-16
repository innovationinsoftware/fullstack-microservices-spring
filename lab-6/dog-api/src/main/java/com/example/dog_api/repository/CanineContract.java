package com.example.dog_api.repository;

public interface CanineContract<T> {
    public void save(T canine);
    public T findById(Long id);
    public java.util.Collection<T> getAll();
}
