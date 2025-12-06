# Lab 2 - Discovery
In this lab you'll set up a Eureka Server and register two services with it.

## Part 1 - Add Eurekea Client
In `payroll-api` locate `build.gradle`.
Once you have located the file, add a new dependency for discovery:
`org.springframework.cloud:spring-cloud-starter-netflix-eureka-client`
Next, while the default port is 8081, we are going to make it 8081 due to new services we will be adding.
Locate:
`payroll-api->src->main->resources->application.yaml`
In that file, add port 8081 as the port for the application:
```yaml
server:
  port: 8081
```
