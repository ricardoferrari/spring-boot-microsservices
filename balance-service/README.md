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

# Use pgAdmin
host: localhost:5432
user: postgres
passwd: example

# Dockerizando a aplicação
Primeiro cria-se o arquivo .jar
$ mvn package

Cria-se a imagem docker
$ docker build -t client-service .

Roda o container
$ docker run balance-service -it --rm -p 8081:8081
$ docker run -it --rm -p 8081:8081 balance-service

# Rodando o DynamoDB localmento
Mudar para o diretório "/dynamodb": $ cd dynamodb
Executar o docker
$ docker-compose up

Para verificar a instância local em execução: $ aws dynamodb list-tables --endpoint-url http://localhost:8000

# Adicionando autenticação e autorização ao serviço
Rodar o container do Keycloak: $ docker-compose -f ./keycloak/docker-compose.yml up

Verificar documentação adicional na pasta Keycloak