package com.example.human_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.human_api.entity.Human;
import com.example.human_api.repository.HumanRepository;

@RestController
@RequestMapping("/humans")
public class HumanController {
    private final HumanRepository humanRepository;

    public HumanController(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @GetMapping("/{id}")
    public Human getHumanById(@PathVariable("id") Long id) {
        return humanRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Human> getAllHumans() {
        return humanRepository.findAll();
    }

    @GetMapping("/owners")
    public List<Human> getAllOwners() {
        return humanRepository.findByOwnerTrue();
    }

    @PostMapping
    public Human createHuman(@RequestBody Human human) {
        return humanRepository.save(human);
    }

    @PutMapping("/{id}")
    public Human updateHuman(@PathVariable("id") Long id, @RequestBody Human human) {
        human.setId(id);
        return humanRepository.save(human);
    }

    @DeleteMapping("/{id}")
    public void deleteHuman(@PathVariable("id") Long id) {
        humanRepository.deleteById(id);
    }
}
