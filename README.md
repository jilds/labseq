# Labseq

## Requirements
For building and running the application using **maven** you need:
- [JDK 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [Maven 3](https://maven.apache.org)

For building and running the application using **Docker** you need:
- [Docker](https://www.docker.com/)

## Running the Application

### Running using maven wrapper
```sh
./mvnw spring-boot:run
```

### Running using docker
build image
```sh
docker build -t labseq-api .
```
generate and up the container
```sh
docker run --name labseq-api -p 8080:8080 -d labseq-api
```
see logs
```sh
docker logs labseq-api
```
Stop app
```sh
docker stop labseq-api
```
Start again
```sh
docker start labseq-api
```

## Access the Swagger Documentation
URL to swagger: http://localhost:8080/swagger-ui/index.html