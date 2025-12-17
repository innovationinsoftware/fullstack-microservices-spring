# Lab 7 - OpenAPI and Swagger
In this lab you will add OpenAPI support to your microservice, then test your services with a Swagger test page. Finally, you will add some more method support and use the Swagger page to test and debug.

## Step 1 - Add Need Package
1. Add the following package to you application.

`org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6`

2. Then build and run the solution.
3. In your browser, go to the [Swagger Page](http://localhost:8080/swagger-ui/index.html)
4. Browse through the documentation, try out a method.
5. Under the title, there's a small link to the [Schema](http://localhost:8080/v3/api-docs)
6. Use the "pretty print" option to browse the schema definition. This can be consumed by clients that understand OpenAPI.

## Step 2 - Add more supported methods
1. Add a DELETE method that will remove a dog from the datastore.
2. Add a PUT method that will update an existing dog based on id.
3. Use the Swagger page to test and verify the new API methods.

## Step 3 - Add additional information
It is possible to tag the services, both at the class level, and at the method level. For microservices, tags at the class level tend to be less useful, as their may only be one class published. We'll add some tags at the method level.

1. Tag all the methods that read data with a 'Read Operations' tag.
```java
@Tag(name = "Read Operations")
```
Note: will need an import for this:
```java
import io.swagger.v3.oas.annotations.tags.Tag;
```

2. Tag all the methods that modify data with a 'Write Operations' tag.
3. Build and run and view the difference on the Swagger page.

## Step 4 - Document the Dog model
1. Update the Dog model to have a minium and maximum string length for 'breed'.
```java
@Size(min = 0, max = 50)
```
2. Build and run.
3. Scroll down to the schema portion at the bottom of the Swagger page and expand the Dog model.
4. Notice the additional information added to the model definition.

# Conclusion
Congratulations, you've added OpenAPI support to your microservice and used Swagger to help you add two new methods.