# Lab 5 - Basic Repository Injection
In this lab, we will create a data repository that has hard coded data that will be injected into a controller.

## Step 1 - Add the Spring-Context
Add a gradle dependency of `org.springframework:spring-context`

This will contain the needed beans for the repository annotation.

## Step 2 - Create the POJO
We will start with a `Dog` entity.
- From the `dog_api` folder, create a new folder called `entity`
- Add a new Java class called `Dog` with the following properties:
    - id - Long
    - name - String
    - breed - String
    - age - Integer
    - color - String
- Once you have the POJO written, add a construtor to take all five properites as initalizers.

```java
public class Dog {
    private Long id;
    private String name;
    private String breed;
    private Integer age;
    private String color;

    public Dog(Long id, String name, String breed, Integer age, String color) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
    }
    ...
}
```

## Step 3 - Create the Interface
Create the interface that the repository will implment in a new folder called `repository`.

```java
public interface CanineContract<T> {
    public void save(T canine);
    public T findById(Long id);
}
```

## Step 4 - Create the Repository
Create the implmentation of the CanineContract to store dogs. This will go in the `repository` folder. Optionally seed it with some data.

```java
@Repository
public class DogRepository implements CanineContract<Dog> {
    private Map<Long, Dog> dogStorage = new HashMap<>();

    public DogRepository() {
        Dog dog1 = new Dog(1L, "Ringo", "Golden Retriever", 3, "Golden");
        Dog dog2 = new Dog(2L, "Mimi", "German Shepherd", 5, "Black and Tan");
        dogStorage.put(dog1.getId(), dog1);
        dogStorage.put(dog2.getId(), dog2);
    }
...
}
```
Finish implementing the CanineContract. Note the `@Repository` annotation to tell Spring Boot what this class is for.

## Step 5 - Add API calls
Create a REST DogController that takes a DogRepository and assigns it to a CanineContract. Then add a get by id, get all, and add dog.

```java
@RestController
@RequestMapping("/dogs")
public class DogController {

    private final CanineContract<Dog> dogRepository;

    public DogController(CanineContract<Dog> dogRepository) {
        ...
    }

    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable Long id) {
        ...
    }

   ...
}
```

## Step 6 - Test
Use Postman to test the three REST endpoints for the DogController.

# Conclusion
Congratulations! You have implmented a Dog API with the ability to add and view dogs. You used the Controller, an interface, and a repository pattern.