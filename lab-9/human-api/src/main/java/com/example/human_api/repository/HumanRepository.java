package com.example.human_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.human_api.entity.Human;

public interface HumanRepository extends JpaRepository<Human, Long> {
    List<Human> findByOwnerTrue();
}
