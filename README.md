# Simian DNA Checker

This codebase was created to demonstrate DNA validation for simians through a REST API.

![Maven build workflow](https://github.com/diegocamara/dna-checker/actions/workflows/maven.yml/badge.svg?branch=main)

# How it works

This application uses Spring Framework on the Java 11 platform and some more modules like:

* Hibernate
* Spring Data JPA
* H2 Database
* Project Lombok
* Jacoco
* PostgreSQL

### Project structure:

```
application/            -> business orchestration layer
+-- web/                -> web layer models and controllers
domain/                 -> core business implementation layer
+-- model/              -> core business entity models
+-- feature/            -> all features logic implementation
+-- exception/          -> all business exceptions
infrastructure/         -> technical details layer
+-- repository/         -> adapters for domain repositories
+-- web/                -> web layer infrastructure models
```

# Getting started

### Start local application server with h2 database

```bash
./mvnw spring-boot:run
```

### Running the application tests and building .jar

```bash
./mvnw clean package
```

After building the application, the coverage reports can be found at:

```
target/site/jacoco
```

To use PostgreSQL as database, the configuration data must be replaced in application.yaml:

```yaml
server:
  port: ${SERVER_PORT:8080}
spring:
  jpa:
    hibernate:
      ddl-auto: update
  datasource:
    username: ${DB_USERNAME:sa}
    password: ${DB_PASSWORD:}
    url: ${DB_JDBC_URL:jdbc:h2:mem:dna_checker_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE}
```