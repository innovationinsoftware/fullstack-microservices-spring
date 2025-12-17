package com.example.dog_api.repository;

public interface CanineContract<T> {
    public void save(T canine);
    public void update(Long id, T canine);
    public T findById(Long id);
    public void deleteById(Long id);
    public java.util.Collection<T> getAll();
}
