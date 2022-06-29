## Spring Microservices for Clients Management

## Introduction

CRUD and notifications

## Initial Configuration

1.	Apache Maven (http://maven.apache.org)
2.	Git Client (http://git-scm.com)

## How To Use

```bash
# Install dependencies
$ mvn install
# Install without testing
$ mvn install -DskipTests

# Run the app
$ mvn spring-boot:run
or 
$ java -jar target/licensing-service-0.0.1-SNAPSHOT.jar
```
## Using dockerized postgres
$ docker pull postgres
$ docker-compose -f postgres.yml up