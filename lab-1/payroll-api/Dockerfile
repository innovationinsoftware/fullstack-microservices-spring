# Multi-stage Dockerfile
# Stage 1: build the fat JAR using a Gradle builder image
FROM gradle:9.2.1-jdk25 AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . .

# Build the Spring Boot jar (skip tests to speed up image builds; remove -x test if you want tests)
RUN gradle bootJar --no-daemon -x test

# Stage 2: small Alpine-based runtime using BellSoft Liberica JRE (Alpine)
FROM bellsoft/liberica-openjdk-alpine:25

ARG JAR_FILE=build/libs/*.jar
WORKDIR /app

# Copy the jar built in the previous stage
COPY --from=builder /home/gradle/project/build/libs/*.jar ./app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Create non-root user and switch to it
RUN addgroup -S app && adduser -S app -G app
USER app

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
