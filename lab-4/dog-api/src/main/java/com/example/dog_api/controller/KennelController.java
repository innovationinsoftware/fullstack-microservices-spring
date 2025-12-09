package com.example.dog_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dog_api.config.KennelProperties;

@RestController
public class KennelController {
    private final KennelProperties kennel;

    public KennelController(KennelProperties kennel) {
        this.kennel = kennel;
    }

    @GetMapping("/kennel")
    public KennelProperties getKennel() {
        return kennel;
    }
}