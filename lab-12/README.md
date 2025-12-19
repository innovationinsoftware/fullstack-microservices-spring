# Lab 12 - Unit Tests
In this lab you will create a series of unit tests for the DogController.

## Step 1 - Set up Test Class
1. Expand the test folder in the project.
2. Add a new folder off `example/dog_api` called `controller`
3. In `controller` add a new Java class called `DogControllerTests`
4. In the new file add the necessary imports.
```java
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
```

5. Decorate the class with the necessary attributes.
```java
@SpringBootTest
@AutoConfigureMockMvc
public class DogControllerTests {
  ...
}
```

## Step 2 - Add the target and supporting fields
We need to set up a mock for the repository, so that we don't try to reach the database. We will also need a test object to manipulate. The @BeforeEach annotation tells the test runner to run that function before each test is run.
1. Add needed supporting fields.
```java
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DogRepository dogRepository;

    private ObjectMapper objectMapper;
    private Dog testDog;
```
2. Add the before each method to set up the test Dog object.
```java
    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        testDog = new Dog("1", "Buddy", "Golden Retriever", 3, "Brown");
    }
```

## Step 3 - Write your first test.
1. Write a test that verifies the values in the JSON object retured when fetching a Dog.
```java
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
```
2. Right mouse click on the test file in the explorer and run tests. It should pass.
3. Write a test to handle a not found result
```java

    @Test
    public void testGetDogById_NotFound() throws Exception {
        when(dogRepository.findById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/dogs/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
```
4. Write a test for get all
```java
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
```
5. Run the tests and verify.
## Step 4 - Write more test of your own.
Write additional test to exercise the controller. Make sure all paths and methods are well covered. Some suggested tests are:
1. testAddDog()
2. testUpdateDogSuccess()
3. testUpdateDogNotFound()
4. testDelete()
An so forth...

# Conclusion
You've written a test suite with mocks to unit test your controller.