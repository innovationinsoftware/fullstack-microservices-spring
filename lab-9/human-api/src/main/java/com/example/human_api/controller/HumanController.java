package com.example.human_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/humans")
public class HumanController {

    @GetMapping("/{id}")
    public String getHumanById(@PathVariable("id") Long id) {
        return "Get human with ID: " + id;
    }

    @GetMapping
    public String getAllHumans() {
        return "Get all humans";
    }
}
