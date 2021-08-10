# Testing UI and microservices [![CI](https://github.com/daggerok/testcontainers-selenide-remote/actions/workflows/ci.yaml/badge.svg)](https://github.com/daggerok/testcontainers-selenide-remote/actions/workflows/ci.yaml)

This project demonstrates how to test REST API microservices using spring-boot, feign client, spring-bot-test, WireMock
and rest-assured. Also current project demonstrates how to test web application UIs using TestContainers, selenide with
Kotlin and maven

## Development

### Testing / Documentation

```bash
./mvnw clean package
open greeting/greeting-service/target/docs/
```

### Integration testing using docker and docker-compose

```bash
./mvnw -DskipTests docker:remove clean package docker:build
./mvnw -f infrastructure/docker-compose docker-compose:up
```

Then test _nginx-reverse-proxy_:

```bash
http :/
http :/greet
http :/greeting-service/api/v1/greetings/greet name=Maksim
```

_cleanup_:

```bash
./mvnw docker-compose:down
./mvnw docker:remove
docker rm -f -v `docker ps -aq`
docker rmi -f `docker images -q -f dangling=true`
```

### End-to-end testing using testcontainers, kotlin, selenide and spring-boot-test

```bash
rm -rf ~/.m2/repository/example
./mvnw clean install # run all non-e2e test and install required deps locally...
./mvnw -f webapp test -P e2e # run e2e tests only...
```

_cleanup_:

```bash
./mvnw docker-compose:down
./mvnw docker:remove
docker rm -f -v `docker ps -aq`
./mvnw clean
docker rmi -f `docker images -q -f dangling=true`
rm -rf ~/.m2/repository/example
```

## RTFM

* https://github.com/selenide-examples/testcontainers/blob/master/src/test/java/org/selenide/examples/DownloadTestWithDockerAndProxy.java
* https://www.testcontainers.org/features/networking/#exposing-host-ports-to-the-container
* https://testcontainers.slack.com/archives/C1SUBPZK6/p1628065705024900
* https://medium.com/javarevisited/configuration-properties-with-kotlin-10fe6176f4f1
* https://auth0.com/blog/beating-json-performance-with-protobuf/
