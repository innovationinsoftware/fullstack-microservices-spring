package com.example.dog_api.controller;

import com.example.dog_api.entity.Dog;
import com.example.dog_api.repository.DogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class DogControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DogRepository dogRepository;

    private ObjectMapper objectMapper;
    private Dog testDog;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        testDog = new Dog("1", "Buddy", "Golden Retriever", 3, "Brown");
    }

    @Test
    public void testGetDogById_Success() throws Exception {
        when(dogRepository.findById("1")).thenReturn(Optional.of(testDog));

        mockMvc.perform(get("/dogs/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Buddy"))
                .andExpect(jsonPath("$.breed").value("Golden Retriever"))
                .andExpect(jsonPath("$.age").value(3))
                .andExpect(jsonPath("$.color").value("Brown"));
    }

    @Test
    public void testGetDogById_NotFound() throws Exception {
        when(dogRepository.findById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/dogs/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testGetAllDogs() throws Exception {
        Dog dog2 = new Dog("2", "Max", "Labrador", 5, "Black");
        when(dogRepository.findAll()).thenReturn(Arrays.asList(testDog, dog2));

        mockMvc.perform(get("/dogs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Buddy"))
                .andExpect(jsonPath("$[1].name").value("Max"));
    }

    @Test
    public void testAddDog() throws Exception {
        when(dogRepository.save(any(Dog.class))).thenReturn(testDog);

        mockMvc.perform(post("/dogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testDog)))
                .andExpect(status().isOk());

        verify(dogRepository).save(any(Dog.class));
    }

    @Test
    public void testUpdateDog_Success() throws Exception {
        Dog updatedDog = new Dog("1", "Buddy Updated", "Labrador", 4, "Golden");
        when(dogRepository.findById("1")).thenReturn(Optional.of(testDog));
        when(dogRepository.save(any(Dog.class))).thenReturn(updatedDog);

        mockMvc.perform(put("/dogs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedDog)))
                .andExpect(status().isOk());

        verify(dogRepository).findById("1");
        verify(dogRepository).save(any(Dog.class));
    }

    @Test
    public void testUpdateDog_NotFound() throws Exception {
        when(dogRepository.findById("999")).thenReturn(Optional.empty());

        mockMvc.perform(put("/dogs/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testDog)))
                .andExpect(status().isOk());

        verify(dogRepository).findById("999");
    }

    @Test
    public void testDeleteDog() throws Exception {
        doNothing().when(dogRepository).deleteById("1");

        mockMvc.perform(delete("/dogs/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(dogRepository).deleteById("1");
    }
}
