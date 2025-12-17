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

# Conclusion
Congratulations, you've added OpenAPI support to your microservice and used Swagger to help you add two new methods.